package cn.evlight.gateway.center.domain.loadbalance.model.valobj;

import java.util.List;

/**
 * @Description:
 * @Author: evlight
 * @Date: 2024/7/11
 */
public class UpstreamVO {

    private String name;
    private String loadBalanceStrategy;
    private List<String> serverAddresses;

    public UpstreamVO() {
    }

    public UpstreamVO(String name, String loadBalanceStrategy, List<String> serverAddresses) {
        this.name = name;
        this.loadBalanceStrategy = loadBalanceStrategy;
        this.serverAddresses = serverAddresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoadBalanceStrategy() {
        return loadBalanceStrategy;
    }

    public void setLoadBalanceStrategy(String loadBalanceStrategy) {
        this.loadBalanceStrategy = loadBalanceStrategy;
    }

    public List<String> getServerAddresses() {
        return serverAddresses;
    }

    public void setServerAddresses(List<String> serverAddresses) {
        this.serverAddresses = serverAddresses;
    }
}
