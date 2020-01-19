package com.lihaiyang.learn.serviceimpl;


import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.UserDao;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.service.IUserService;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements IUserService {


}

