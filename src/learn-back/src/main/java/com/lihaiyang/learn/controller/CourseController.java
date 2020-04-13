package com.lihaiyang.learn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.dto.CourseDTO;
import com.lihaiyang.learn.dto.CourseInDTO;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.entity.CourseType;
import com.lihaiyang.learn.service.ICourseService;
import com.lihaiyang.learn.service.ICourseTypeService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping({"${adminPath}/course"})
public class CourseController {

    @Autowired
    private ICourseService courseService;


    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO){

        Long courseTypeId = pageDTO.getQueryField("courseTypeId",Long.class);
        Course entity = new Course();
        entity.setCourseTypeId(courseTypeId);
        IPage<Course> listPage = courseService.page(pageDTO.getPage(),entity,pageDTO.getSortSql());
        return Result.ofSuccess(new ResultList<>(listPage));

    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){

        Course course = courseService.getById(id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourse(course);
        return Result.ofSuccess(courseDTO);
    }

    @PostMapping("/save")
    public  Result save(@RequestBody CourseInDTO courseInDTO){
        String name = courseInDTO.getName();
        Long typeId = courseInDTO.getTypeId();
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String f=df.format(date);
        Course course = new Course();
        course.setCourseTypeId(typeId);
        course.setName(name);
        course.setCreateTime(f);
        courseService.save(course);
        return  Result.ofSuccess("保存成功");
    }





}
