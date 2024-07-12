package cn.evlight.gateway.center.domain.loadbalance.service.impl;

import cn.evlight.gateway.center.app.config.nginx.NginxProperties;
import cn.evlight.gateway.center.domain.loadbalance.model.valobj.GatewayServerDetailVO;
import cn.evlight.gateway.center.domain.loadbalance.model.valobj.LocationVO;
import cn.evlight.gateway.center.domain.loadbalance.model.valobj.UpstreamVO;
import cn.evlight.gateway.center.domain.loadbalance.service.AbstractLoadBalanceService;
import cn.evlight.gateway.center.types.constant.Character;
import cn.evlight.gateway.center.types.constant.NginxConstant;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: nginx负载均衡服务
 * @Author: evlight
 * @Date: 2024/7/11
 */
@Service
public class NginxLoadBalanceServiceService extends AbstractLoadBalanceService {

    private final Logger logger = LoggerFactory.getLogger(NginxLoadBalanceServiceService.class);
    @Autowired
    private NginxProperties nginxProperties;

    @Override
    protected String createConfig(List<GatewayServerDetailVO> gatewayServerDetailVOS) throws Exception {
        logger.info("[更新负载均衡配置]-[创建配置文件] 开始");
        Map<String, List<GatewayServerDetailVO>> groups = gatewayServerDetailVOS.stream()
                .collect(Collectors.groupingBy(GatewayServerDetailVO::getGroupId));
        List<LocationVO> locationVOS = groups.keySet()
                .stream()
                .map(groupId -> new LocationVO(Character.BACKSLASH + groupId + Character.BACKSLASH,
                        NginxConstant.Key.HTTP_PROTOCOL_PREFIX + groupId + Character.SEMICOLON))
                .collect(Collectors.toList());
        List<UpstreamVO> upstreamVOS = groups.keySet()
                .stream()
                .map(groupId -> {
                    List<GatewayServerDetailVO> gateways = groups.get(groupId);
                    List<String> serverAddresses = gateways.stream()
                            .map(GatewayServerDetailVO::getGatewayAddress)
                            .collect(Collectors.toList());
                    return new UpstreamVO(groupId,
                            NginxConstant.LoadBalanceStrategy.LEAST_CONNECTIONS,
                            serverAddresses
                    );
                }).collect(Collectors.toList());
        String config = buildConfig(locationVOS, upstreamVOS);
        File file = new File("F:\\3_next\\SpringCloud\\Code\\api-gateway\\docs\\nginx\\conf\\nginx.conf");
        if (!file.exists()) {
            boolean success = file.createNewFile();
            if (!success) {
                logger.error("[更新负载均衡配置]-[创建配置文件] 配置文件创建失败");
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(config);
        fileWriter.close();
        logger.info("[更新负载均衡配置]-[创建配置文件] 成功");
        return file.getAbsolutePath();
    }

    @Override
    protected void refreshConfig() throws Exception {
        logger.info("[更新负载均衡配置]-[刷新配置文件] 开始");
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("unix:///var/run/docker.sock")
                .build();
        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();
        String containerId = dockerClient.listContainersCmd()
                .withNameFilter(new ArrayList<String>() {{
                    add(nginxProperties.getContainerName());
                }})
                .exec()
                .get(0)
                .getId();
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient
                .execCreateCmd(containerId)
                .withCmd("nginx", "-s", "reload")
                .exec();
        dockerClient.execStartCmd(execCreateCmdResponse.getId())
                .exec(new ResultCallback.Adapter<>()).awaitCompletion();
        dockerClient.close();
        logger.info("[更新负载均衡配置]-[刷新配置文件] 成功");
    }

    private String buildConfig(List<LocationVO> locationVOS, List<UpstreamVO> upstreamVOS) {
        //location
        StringBuilder locationStr = new StringBuilder();
        for (LocationVO location : locationVOS) {
            locationStr.append("\t").append("\t").append(NginxConstant.Key.LOCATION).append(Character.SPACE).append(location.getPath()).append(" {\r\n");
            locationStr.append("\t").append("\t").append("\t").append(NginxConstant.Key.REWRITE).append(location.getPath()).append("(.*)$ /$1 break;").append("\r\n");
            locationStr.append("\t").append("\t").append("\t").append(NginxConstant.Key.PROXY_PASS).append(Character.SPACE).append(location.getProxyPass()).append("\r\n");
            locationStr.append("\t").append("\t").append("}").append("\r\n").append("\r\n");
        }
        //upstream
        StringBuilder upstreamStr = new StringBuilder();
        for (UpstreamVO upstream : upstreamVOS) {
            upstreamStr.append("\t").append(NginxConstant.Key.UPSTREAM).append(Character.SPACE).append(upstream.getName()).append(" {\r\n");
            upstreamStr.append("\t").append("\t").append(upstream.getLoadBalanceStrategy()).append("\r\n").append("\r\n");
            List<String> servers = upstream.getServerAddresses();
            for (String server : servers) {
                upstreamStr.append("\t").append("\t").append(NginxConstant.Key.SERVER).append(Character.SPACE).append(server).append(";\r\n");
            }
            upstreamStr.append("\t").append("}").append("\r\n").append("\r\n");
        }
        String config = NginxConstant.CONFIG;
        config = config.replace(NginxConstant.SERVER_NAME_PLACEHOLDER, nginxProperties.getServerName());
        config = config.replace(NginxConstant.LOCATION_CONFIG_PLACEHOLDER, locationStr.toString());
        config = config.replace(NginxConstant.UPSTREAM_CONFIG_PLACEHOLDER, upstreamStr.toString());
        return config;
    }
}
