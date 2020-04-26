package com.lihaiyang.learn.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_comment")
@Data
public class Comment extends BaseEntity<Comment> {

    private static final long serialVersionUID = 1L;

    private String comment;

    private Long videoId;

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

    private Long createTo;

    private Long parentId;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String toName;

    @TableField(exist = false)
    private String profilePhoto;


}
