package com.lihaiyang.learn.aspect;


import com.lihaiyang.learn.annotation.ChapterAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ChapterAspect {
    ;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.lihaiyang.learn.annotation.ChapterAnnotation)")
    public void Pointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    @Before(value = "Pointcut()&&@annotation(chapterAnnotation)")
    public void before(JoinPoint point, ChapterAnnotation chapterAnnotation) {
        ChapterAnnotation.Type type = chapterAnnotation.type();
        Object[] args = point.getArgs();
        switch (type) {
            case ADD:
                break;
            case DELETE:
                break;
        }
    }

}

