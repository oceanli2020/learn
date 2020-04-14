package com.lihaiyang.learn.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.entity.Chapter;
import com.lihaiyang.learn.service.IChapterCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${adminPath}/chapter"})
public class ChapterController {

    @Autowired
    private  IChapterCourse chapterCourse;

    @GetMapping("/{courseId}")
    public Result getByCourseId(@PathVariable Long courseId){
        Chapter query = new Chapter();
        query.setCourseId(courseId);
        return Result.ofSuccess(chapterCourse.list(Wrappers.query(query)));
    }
}
