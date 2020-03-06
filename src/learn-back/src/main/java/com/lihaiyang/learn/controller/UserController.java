package com.lihaiyang.learn.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.config.MobileProperties;
import com.lihaiyang.learn.core.convert.ObjectConvert;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.utils.PasswordUtils;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.dto.UserDTO;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"${adminPath}/sys/user"})
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private MobileProperties mobileProperties;


    /**
     * 新增用户
     * */
 /*   @PostMapping
    public Result save(@RequestBody User user) {
        String password = PasswordUtils.entryptPassword(this.mobileProperties.getInitialPassword());
        user.setPassword(password);
        user.setDelFlag("0");
        this.userService.save(user);
        return Result.ofSuccess("保存用户" + user.getUserName() + "成功");
    }*/

    /**
     * 注册用户
     */
    @PostMapping({"register"})
    public List<Result> register(@RequestBody User user) {
        List<Result> rs = this.userService.checkUser(user);
        if (rs.size() != 0) {
            return rs;
        }
        String password = PasswordUtils.entryptPassword(user.getPassword());
        user.setPassword(password);
        user.setDelFlag("0");
        this.userService.save(user);
        rs.add(Result.ofSuccess("用户" + user.getUserName() + "注册成功"));
        return rs;
    }


    /**
     * 当前用户信息
     */
    @GetMapping({"info"})
    public Result info() {
        UserDTO userDTO = new UserDTO();
        User user = UserUtils.getUser();
        if (user == null) {
            return Result.ofSuccess(null);
        }
        ObjectConvert<UserDTO> objectToDto = new ObjectConvert();
        userDTO = (UserDTO) objectToDto.toDto(user, userDTO);
        return Result.ofSuccess(userDTO);
    }


}
