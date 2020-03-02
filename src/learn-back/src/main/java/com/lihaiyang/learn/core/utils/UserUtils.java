package com.lihaiyang.learn.core.utils;


import com.lihaiyang.learn.core.account.AccountLocals;
import com.lihaiyang.learn.entity.User;

public class UserUtils {

    public UserUtils() {
    }

    public static User getUser() {
        return (User) AccountLocals.getAccount();
    }



}



