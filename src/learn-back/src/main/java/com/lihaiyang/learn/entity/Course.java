package com.lihaiyang.learn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

@TableName(value = "sys_course")
@Data
public class Course extends BaseEntity<Course> {
    private static final long serialVersionUID = 1L;

    private String name;
    private Double price;
}
