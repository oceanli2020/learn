package com.lihaiyang.learn.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@TableName(value = "sys_replay")
@Data
public class Replay extends BaseEntity<Replay> {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long duration;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8"
    )
    private Date createDate;

    private String path;

    private Long liveId;
}
