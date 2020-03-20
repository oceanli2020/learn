package com.lihaiyang.learn.dto;

import com.lihaiyang.learn.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {

    private List<Course> courseList;

    private Integer total;


}
