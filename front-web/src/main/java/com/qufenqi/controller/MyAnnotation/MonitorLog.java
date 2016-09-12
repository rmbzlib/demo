package com.qufenqi.controller.MyAnnotation;

import java.lang.annotation.*;

/**
 * Created by liuxin on 16/9/10.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited // 继承
public @interface MonitorLog {

    String desc() default "";
    String[] param() default{};
}
