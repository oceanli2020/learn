package com.lihaiyang.learn.service;


import com.lihaiyang.learn.core.base.service.IBaseService;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.entity.User;

import java.util.List;


public interface IUserService extends IBaseService<User> {

    List<Result> checkUser(User user);



}
