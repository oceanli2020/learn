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

    @TableField(
            fill = FieldFill.INSERT
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createDate;
}
