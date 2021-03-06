package com.lihaiyang.learn.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_video")
@Data
public class Video extends BaseEntity<Video> {

    private static final long serialVersionUID = 1L;

    private  String name;

    private  Long ChapterId;

    private  String introduction;

    private  String path;

    @TableField(
            fill = FieldFill.INSERT
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8"
    )
    private Date createDate;

    @TableField(exist = false)
    private Long likeAmount;

    @TableField(exist = false)
    private  Long collectAmount;

    @TableField(exist = false)
    private  String  courseTypeName;

    @TableField(exist = false)
    private  Long courseId;

}
