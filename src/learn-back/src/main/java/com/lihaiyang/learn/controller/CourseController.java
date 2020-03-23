package com.lihaiyang.learn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.dto.CourseDTO;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.entity.CourseType;
import com.lihaiyang.learn.service.ICourseService;
import com.lihaiyang.learn.service.ICourseTypeService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"${adminPath}/course"})
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseTypeService courseTypeService;


    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO){

        Long courseTypeId = pageDTO.getQueryField("courseTypeId",Long.class);
        Course entity = new Course();
        entity.setCourseTypeId(courseTypeId);
        IPage<Course> listPage = courseService.page(pageDTO.getPage(),entity,pageDTO.getSortSql());
        listPage.getRecords().forEach(course -> {
            if(course.getPrice()==0){
                course.setPriceString("免费");
            }else {
                course.setPriceString("￥"+course.getPrice());
            }
        });
        return Result.ofSuccess(new ResultList<>(listPage));

    }

    @GetMapping("/{courseId}")
    public Result get(@PathVariable Long courseId){

        Course course = courseService.getById(courseId);
        Long courseTypeId = course.getCourseTypeId();
        CourseType courseType = courseTypeService.getById(courseTypeId);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseTypeName(courseType.getName());
        courseDTO.setCourse(course);
        return Result.ofSuccess(courseDTO);
    }





}
