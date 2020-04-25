package com.lihaiyang.learn.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_course")
@Data
public class Course extends BaseEntity<Course> {
    private static final long serialVersionUID = 1L;

    private String name;

    private Long courseTypeId;

    private  String introduction;

    @TableField(
            fill = FieldFill.INSERT
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    private Date createDate;

    @TableField(
            fill = FieldFill.INSERT
    )
    private Long createBy;

    @TableField(exist = false)
    private  Integer  chapterCount;

    @TableField(exist = false)
    private  String  courseTypeName;

    @TableField(exist = false)
    private Boolean isSubscribe;

    @TableField(exist = false)
    private Long subscribeAmount;

    @TableField(exist = false)
    private  Long likeCount;

    @TableField(exist = false)
    private  String userName;



}
