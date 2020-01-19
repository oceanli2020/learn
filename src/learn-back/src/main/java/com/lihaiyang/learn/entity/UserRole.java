package com.lihaiyang.learn.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;

@TableName("sys_user_role")
@Data
public class UserRole extends BaseEntity<UserRole> {

    private Long userId;
    private Long roleId;

}
