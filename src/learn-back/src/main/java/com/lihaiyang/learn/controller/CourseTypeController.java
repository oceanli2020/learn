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

    @GetMapping("/children/{parentId}")
    public Result getChildren(@PathVariable Long parentId){

        CourseType courseType = new CourseType();
        courseType.setParentId(parentId);
        return Result.ofSuccess(courseTypeService.list(Wrappers.query(courseType)));

    }

    @GetMapping("/parents/{id}")
    public Result getParents(@PathVariable Long id){
        return  Result.ofSuccess(courseTypeService.getParentsById(id));
    }
}
