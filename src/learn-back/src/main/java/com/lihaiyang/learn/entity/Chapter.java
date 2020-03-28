package com.lihaiyang.learn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_chapter")
@Data
public class Chapter extends BaseEntity<Chapter> {
    private static final long serialVersionUID = 1L;

    private String name;

    private Long courseId;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createDate;

}
