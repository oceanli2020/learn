package com.lihaiyang.learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseAnnotation {

    CourseAnnotation.Type type() default Type.CourseAnnotation;

    public static enum Type {
        ADD,
        DELETE,
        CourseAnnotation;
    }
}
