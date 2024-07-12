package cn.evlight.gateway.sdk.application.annotation;

import java.lang.annotation.*;

/**
 * @Description: 方法
 * @Author: evlight
 * @Date: 2024/7/9
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ProducerMethod {

    String methodName() default "";

    String uri() default "";

    String httpRequestType() default "";

    int auth() default 1;

}
