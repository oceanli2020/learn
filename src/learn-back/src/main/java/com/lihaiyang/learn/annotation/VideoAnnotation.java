package com.lihaiyang.learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VideoAnnotation {

    VideoAnnotation.Type type() default Type.VideoAnnotation;

    public static enum Type {
        ADD,
        DELETE,
        VideoAnnotation;
    }
}
