package com.lihaiyang.learn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"${adminPath}/course"})
public class CourseController {

    @Autowired
    private ICourseService courseService;


    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO){

        IPage<Course> listPage = courseService.page(pageDTO.getPage());
        return Result.ofSuccess(new ResultList<>(listPage));

    }


}
