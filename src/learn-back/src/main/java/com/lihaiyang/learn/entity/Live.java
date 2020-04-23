package com.lihaiyang.learn.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

@TableName(value = "sys_live")
@Data
public class Live  extends BaseEntity<Live> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String pushCode;

    private String rtmp;

    private String introduction;

    private String isStart;

    @TableField(
            fill = FieldFill.INSERT
    )
    private Long createBy;


}
