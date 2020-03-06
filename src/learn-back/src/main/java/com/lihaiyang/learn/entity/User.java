package com.lihaiyang.learn.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lihaiyang.learn.core.account.Account;
import com.lihaiyang.learn.core.base.entity.BaseEntity;
import lombok.Data;


@TableName(value = "sys_user")
@Data
public class User extends BaseEntity<User> implements Account {
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private String realName;
    private String email;
    private String phoneNumber;
    private String profilePhoto;

    @Override
    public User getAccount() {
        return this;
    }
}
