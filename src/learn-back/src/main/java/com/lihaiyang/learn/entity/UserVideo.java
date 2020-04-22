package com.lihaiyang.learn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "user_video")
@Data
public class UserVideo {

    private Long userId;

    private Long videoId;

    private String isLike;

    private String isCollect;

    @TableField(exist = false)
    private Long likeAmount;

    @TableField(exist = false)
    private  Long collectAmount;

}
