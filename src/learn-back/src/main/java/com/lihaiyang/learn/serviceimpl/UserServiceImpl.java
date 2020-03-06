package com.lihaiyang.learn.serviceimpl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultStatus;
import com.lihaiyang.learn.dao.UserDao;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements IUserService {

    @Override
    public List<Result> checkUser(User user) {
        List<Result> rs = new LinkedList<>();
        User query1 = new User();
        query1.setUserName(user.getUserName());
        if (super.getOne(Wrappers.query(query1)) != null) {
            rs.add(new Result(ResultStatus.USER_ALREADY_EXIST, "注册失败"));
        }
        User query2 = new User();
        query2.setEmail(user.getEmail());
        if (super.getOne(Wrappers.query(query2)) != null) {
            rs.add(new Result(ResultStatus.EMAIL_ALREADY_EXIST, "注册失败"));
        }
        User query3 = new User();
        query3.setPhoneNumber(user.getPhoneNumber());
        if (super.getOne(Wrappers.query(query3)) != null) {
            rs.add(new Result(ResultStatus.PHONENUMBER_ALREADY_EXIST, "注册失败"));
        }
        return rs;
    }
}

