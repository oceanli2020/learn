package com.lihaiyang.learn.aspect;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.annotation.VideoAnnotation;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.entity.Video;
import com.lihaiyang.learn.service.IVideoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class VideoAspect {

    @Autowired
    private IVideoService videoService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.lihaiyang.learn.annotation.VideoAnnotation)")
    public void Pointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    @Before(value = "Pointcut()&&@annotation(videoAnnotation)")
    public void before(JoinPoint point, VideoAnnotation videoAnnotation) {
        VideoAnnotation.Type type = videoAnnotation.type();
        Object[] args = point.getArgs();
        switch (type) {
            case ADD:
                break;
            case DELETE:
                Long id = (Long) args[0];
                Video videoQuery = new Video();
                videoQuery.setChapterId(id);
                List<Video> videoList = videoService.list(Wrappers.query(videoQuery));
                if(videoList.size()!=0){
                    List<Long> videoIds = new ArrayList<>();
                    videoList.forEach(video -> {
                        videoIds.add(video.getId());
                    });
                    videoService.removeCollectByVideoIdsAndUserId(videoIds, UserUtils.getUser().getId());
                }
                break;
        }
    }

}

