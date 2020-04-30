package com.lihaiyang.learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChapterAnnotation {

    ChapterAnnotation.Type type() default Type.ChapterAnnotation;

    public static enum Type {
        ADD,
        DELETE,
        ChapterAnnotation;
    }
}
