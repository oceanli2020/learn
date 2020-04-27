package com.lihaiyang.learn.core.mybatis;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GlobalFieldIgnore {
    String[] value() default {};
}
