package cn.evlight.gateway.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 启动类
 * @Author: evlight
 * @Date: 2024/7/7
 */
@SpringBootApplication
@Configuration
public class GatewayCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayCenterApplication.class, args);
    }
}
