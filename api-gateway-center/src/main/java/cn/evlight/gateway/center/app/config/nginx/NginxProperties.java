package cn.evlight.gateway.center.app.config.nginx;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: nginx配置参数
 * @Author: evlight
 * @Date: 2024/7/11
 */
@Component
@ConfigurationProperties(prefix = "config.nginx")
public class NginxProperties {

    private String containerName;
    private String serverName;
    private String localConfigPath;
    private String innerConfigPath;

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getLocalConfigPath() {
        return localConfigPath;
    }

    public void setLocalConfigPath(String localConfigPath) {
        this.localConfigPath = localConfigPath;
    }

    public String getInnerConfigPath() {
        return innerConfigPath;
    }

    public void setInnerConfigPath(String innerConfigPath) {
        this.innerConfigPath = innerConfigPath;
    }
}
