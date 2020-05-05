package com.lihaiyang.learn.aspect;

import com.lihaiyang.learn.annotation.CourseAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CourseAspect {

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.lihaiyang.learn.annotation.CourseAnnotation)")
    public void Pointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    @Before(value = "Pointcut()&&@annotation(courseAnnotation)")
    public void before(JoinPoint point, CourseAnnotation courseAnnotation) {
        CourseAnnotation.Type type = courseAnnotation.type();
        Object[] args = point.getArgs();
        switch (type) {
            case ADD:
                break;
            case DELETE:
                break;
        }
    }

}
