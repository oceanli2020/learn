package com.lihaiyang.learn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.dto.CourseDTO;
import com.lihaiyang.learn.dto.CourseInDTO;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.entity.CourseType;
import com.lihaiyang.learn.entity.User;
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

    /**
     * 获取课程列表
     * */
    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO){
        Course entity = new Course();
        Long courseTypeId = pageDTO.getQueryField("courseTypeId",Long.class);
        entity.setCourseTypeId(courseTypeId);
        Long createBy = pageDTO.getQueryField("createBy",Long.class);
        if(createBy!=null){
            User user = UserUtils.getUser();
            entity.setCreateBy(user.getId());
        }
        IPage<Course> listPage = courseService.page(pageDTO.getPage(),entity,pageDTO.getSortSql());
        return Result.ofSuccess(new ResultList<>(listPage));

    }

    /**
     * 根据课程ID获取课程信息
     * */
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        Course course = courseService.getById(id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourse(course);
        return Result.ofSuccess(courseDTO);
    }



    /**
     * 新建课程
     * */
    @PostMapping("/save")
    public  Result save(@RequestBody CourseInDTO courseInDTO){
        String name = courseInDTO.getName();
        Long typeId = courseInDTO.getTypeId();
        Course course = new Course();
        course.setCourseTypeId(typeId);
        course.setName(name);
        courseService.save(course);
        return  Result.ofSuccess("保存成功");
    }





}
