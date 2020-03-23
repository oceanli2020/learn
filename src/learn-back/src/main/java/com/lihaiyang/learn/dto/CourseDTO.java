package com.lihaiyang.learn.dto;

import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.entity.CourseType;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {

    private List<CourseType> courseTypeList;

    private Course course;
}
