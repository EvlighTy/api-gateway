package cn.evlight.gateway.sdk.application.annotation;

import java.lang.annotation.*;

/**
 * @Description: 接口
 * @Author: evlight
 * @Date: 2024/7/9
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ProducerClazz {

    String interfaceName() default "";

    String version() default "";

}
