package cn.evlight.gateway.center.domain.loadbalance.model.valobj;

/**
 * @Description:
 * @Author: evlight
 * @Date: 2024/7/11
 */
public class LocationVO {

    private String path;
    private String proxyPass;

    public LocationVO() {
    }

    public LocationVO(String path, String proxyPass) {
        this.path = path;
        this.proxyPass = proxyPass;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProxyPass() {
        return proxyPass;
    }

    public void setProxyPass(String proxyPass) {
        this.proxyPass = proxyPass;
    }
}
