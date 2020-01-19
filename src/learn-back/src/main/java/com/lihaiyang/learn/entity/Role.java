package com.lihaiyang.learn.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.collect.Maps;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

import java.util.Map;

@TableName("sys_role")
@Data
public class Role extends BaseEntity<Role> {
    private static final long serialVersionUID = 1L;

    private String name;
    private String enname;
    private Long userId;

}
