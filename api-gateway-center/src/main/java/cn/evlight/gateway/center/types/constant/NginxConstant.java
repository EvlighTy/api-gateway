package cn.evlight.gateway.center.types.constant;

/**
 * @Description: nginx常量
 * @Author: evlight
 * @Date: 2024/7/11
 */
public interface NginxConstant {

    String CONFIG = "\n" +
            "user  nginx;\n" +
            "worker_processes  auto;\n" +
            "\n" +
            "error_log  /var/log/nginx/error.log notice;\n" +
            "pid        /var/run/nginx.pid;\n" +
            "\n" +
            "\n" +
            "events {\n" +
            "    worker_connections  1024;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "http {\n" +
            "    include       /etc/nginx/mime.types;\n" +
            "    default_type  application/octet-stream;\n" +
            "\n" +
            "    log_format  main  '$remote_addr - $remote_user [$time_local] \"$request\" '\n" +
            "                      '$status $body_bytes_sent \"$http_referer\" '\n" +
            "                      '\"$http_user_agent\" \"$http_x_forwarded_for\"';\n" +
            "\n" +
            "    access_log  /var/log/nginx/access.log  main;\n" +
            "\n" +
            "    sendfile        on;\n" +
            "    #tcp_nopush     on;\n" +
            "\n" +
            "    keepalive_timeout  65;\n" +
            "\n" +
            "    #gzip  on;\n" +
            "\n" +
            "    include /etc/nginx/conf.d/*.conf;\n" +
            "\n" +
            "    # 设定负载均衡的服务器列表 命令：docker exec Nginx nginx -s reload\n" +
            "upstream_config_placeholder" +
            "\n" +
            "    # HTTP服务器\n" +
            "    server {\n" +
            "        # 监听80端口，用于HTTP协议\n" +
            "        listen  80;\n" +
            "\n" +
            "        # 定义使用IP/域名访问\n" +
            "        server_name server_name_placeholder ;\n" +
            "\n" +
            "        # 首页\n" +
            "        index index.html;\n" +
            "\n" +
            "        # 反向代理的路径（upstream绑定），location 后面设置映射的路径\n" +
            "        # location / {\n" +
            "        #    proxy_pass http://127.0.0.1:7379;\n" +
            "        # }\n" +
            "\n" +
            "location_config_placeholder" +
            "    }\n" +
            "}\n";

    String LOCATION_CONFIG_PLACEHOLDER = "location_config_placeholder";
    String UPSTREAM_CONFIG_PLACEHOLDER = "upstream_config_placeholder";
    String SERVER_NAME_PLACEHOLDER = "server_name_placeholder";

    interface Key {
        String SERVER = "server";
        String UPSTREAM = "upstream";
        String HTTP_PROTOCOL_PREFIX = "http://";
        String LOCATION = "location";
        String PROXY_PASS = "proxy_pass";
        String REWRITE = "rewrite ^";
    }

    interface LoadBalanceStrategy {
        String ROUND_ROBIN = "";
        String LEAST_CONNECTIONS = "least_conn";
        String IP_HASH = "ip_hash";
    }

}
