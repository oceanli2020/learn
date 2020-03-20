package com.lihaiyang.learn.controller;


import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.dto.CourseDTO;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"${adminPath}/course"})
public class CourseController {

    @Autowired
    private ICourseService courseService;


    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO){

        List<Course> list = courseService.list();
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseList(list);
        courseDTO.setTotal(list.size());
        return Result.ofSuccess(courseDTO);

    }


}
