package com.lihaiyang.learn.core.convert;


import java.lang.reflect.Field;
import java.util.Objects;

import com.lihaiyang.learn.core.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;


public class ObjectConvert<T> {


    public T toDto(final Object obj, final T t) {
        return this.toDto(obj, t, true);
    }


    public T toDto(final Object obj, final T t, boolean flag) {
        try {
            Field[] fields;
            Field[] var5;
            int var6;
            int var7;
            Field f;
            Convert convert;
            if (flag) {
                fields = t.getClass().getDeclaredFields();
                var5 = fields;
                var6 = fields.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    f = var5[var7];
                    if (f.isAnnotationPresent(Convert.class)) {
                        convert = (Convert)f.getAnnotation(Convert.class);
                        String fieldName = convert.value();
                        if (StringUtils.isBlank(fieldName)) {
                            fieldName = f.getName();
                        }

                        Object sobj = ReflectionUtils.invokeGetter(obj, fieldName);
                        if (Objects.nonNull(sobj)) {
                            ReflectionUtils.setFieldValue(t, f.getName(), sobj);
                        }
                    }
                }
            } else {
                fields = obj.getClass().getDeclaredFields();
                var5 = fields;
                var6 = fields.length;

                for(var7 = 0; var7 < var6; ++var7) {
                    f = var5[var7];
                    if (f.isAnnotationPresent(Convert.class)) {
                        convert = (Convert)f.getAnnotation(Convert.class);
                        Object sobj = ReflectionUtils.invokeGetter(obj, f.getName());
                        if (Objects.nonNull(sobj)) {
                            String fieldName = convert.value();
                            if (StringUtils.isBlank(fieldName)) {
                                fieldName = f.getName();
                            }

                            ReflectionUtils.setFieldValue(t, fieldName, sobj);
                        }
                    }
                }
            }

            return t;
        } catch (Exception var12) {
            throw new RuntimeException(var12);
        }
    }
}
