package com.lihaiyang.learn.dto;

import com.lihaiyang.learn.entity.Course;
import lombok.Data;

@Data
public class CourseDTO {

    private String courseTypeName;

    private Course course;
}
