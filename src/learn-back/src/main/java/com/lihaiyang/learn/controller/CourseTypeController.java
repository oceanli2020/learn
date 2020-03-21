package com.lihaiyang.learn.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.entity.CourseType;
import com.lihaiyang.learn.service.ICourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"${adminPath}/course/type"})
public class CourseTypeController {

    @Autowired
    private ICourseTypeService courseTypeService;

    @GetMapping("/{parentId}")
    public Result get(@PathVariable Long parentId){

        CourseType courseType = new CourseType();
        courseType.setParentId(parentId);
        return Result.ofSuccess(courseTypeService.list(Wrappers.query(courseType)));

    }
}
