package cn.evlight.gateway.core.session;

import cn.evlight.gateway.core.auth.impl.JwtAuthService;
import cn.evlight.gateway.core.datasource.connection.Connection;
import cn.evlight.gateway.core.executor.Executor;
import cn.evlight.gateway.core.executor.impl.DefaultExecutor;
import cn.evlight.gateway.core.mapper.IGenericReference;
import cn.evlight.gateway.core.mapper.MapperRegistry;
import cn.evlight.gateway.core.mapping.HTTPStatement;
import cn.evlight.gateway.core.type.ConstantString;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 会话生命周期配置
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class Configuration {

    private String hostName = "127.0.0.1"; //网关地址
    private int port = 8081; //网关端口
    private int bossNThread = 1; //主线程数
    private int workNThread = 4; //工作线程数
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>(); //RPC应用服务
    private final Map<String, RegistryConfig> registryConfigHashMap = new HashMap<>(); //RPC注册服务
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigHashMap = new HashMap<>(); //RPC泛化服务
    private final Map<String, HTTPStatement> httpStatementMap = new HashMap<>();
    private final MapperRegistry mapperRegistry = new MapperRegistry(this); //映射注册器
    private final JwtAuthService jwtAuthService = new JwtAuthService();

    public Configuration() {
    }

    public Configuration(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public Configuration(String hostName, int port, int bossNThread, int workNThread) {
        this.hostName = hostName;
        this.port = port;
        this.bossNThread = bossNThread;
        this.workNThread = workNThread;
    }

    public ApplicationConfig getApplicationConfig(String applicationName) {
        return applicationConfigMap.get(applicationName);
    }

    public RegistryConfig getRegistryConfig(String applicationName) {
        return registryConfigHashMap.get(applicationName);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName) {
        return referenceConfigHashMap.get(interfaceName);
    }

    public HTTPStatement getHTTPStatement(String uri) {
        return httpStatementMap.get(uri);
    }

    public void addHTTPStatement(HTTPStatement httpStatement) {
        httpStatementMap.put(httpStatement.getUri(), httpStatement);
    }

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        return mapperRegistry.getMapper(uri, gatewaySession);
    }

    public void addMapper(HTTPStatement httpStatement) {
        mapperRegistry.addMapper(httpStatement);
    }

    public Executor newExecutor(Connection connection) {
        return new DefaultExecutor(this, connection);
    }

    public boolean auth(String uId, String token) {
        return jwtAuthService.identify(uId, token);
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getBossNThread() {
        return bossNThread;
    }

    public void setBossNThread(int bossNThread) {
        this.bossNThread = bossNThread;
    }

    public int getWorkNThread() {
        return workNThread;
    }

    public void setWorkNThread(int workNThread) {
        this.workNThread = workNThread;
    }

    public void registryConfig(String applicationName, String address, String interfaceName, String version) {
        if (null == applicationConfigMap.get(applicationName)) {
            ApplicationConfig applicationConfig = new ApplicationConfig(applicationName);
            applicationConfig.setQosEnable(false);
            applicationConfigMap.put(applicationName, applicationConfig);
        }
        if (null == registryConfigHashMap.get(applicationName)) {
            RegistryConfig registryConfig = new RegistryConfig(address);
            registryConfig.setRegister(false);
            registryConfigHashMap.put(applicationName, registryConfig);
        }
        if (null == referenceConfigHashMap.get(interfaceName)) {
            ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
            referenceConfig.setInterface(interfaceName);
            referenceConfig.setGeneric(ConstantString.Boolean.TRUE);
            referenceConfig.setVersion(version);
            referenceConfigHashMap.put(interfaceName, referenceConfig);
        }

    }
}
