package com.lihaiyang.learn.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

@TableName(value = "sys_course_type")
@Data
public class CourseType extends BaseEntity<CourseType> {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long parentId;

    private  String parentIds;

}
