package com.lihaiyang.learn.controller;


import com.lihaiyang.learn.core.config.MobileProperties;
import com.lihaiyang.learn.core.convert.ObjectConvert;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultStatus;
import com.lihaiyang.learn.core.utils.PasswordUtils;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.dto.UserDTO;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

@RestController
@RequestMapping({"${adminPath}/sys/user"})
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private MobileProperties mobileProperties;


    /**
     * 用户注册
     */
    @PostMapping({"/register"})
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
     * 获取当前用户信息
     */
    @GetMapping
    public Result info() {
        UserDTO userDTO = new UserDTO();
        User user = UserUtils.getUser();
        ObjectConvert<UserDTO> objectToDto = new ObjectConvert();
        userDTO = (UserDTO) objectToDto.toDto(user, userDTO);
        return Result.ofSuccess(userDTO);
    }

    /**
     * 当前用户密码验证
     */
    @GetMapping({"/checkpass"})
    public Result checkPass(@RequestParam("passwordInput") String passwordInput) {
        User user = UserUtils.getUser();
        if (PasswordUtils.validatePassword(passwordInput, user.getPassword())) {
            return Result.ofSuccess("密码正确");
        }
        return new Result(ResultStatus.USER_PASS_EDIT_FAIL, "密码错误");

    }

    /**
     * 用户修改个人信息
     */
    @PutMapping
    public Result update(@RequestBody User userInput) {
        if (userInput.getPassword() != null) {
            userInput.setPassword(PasswordUtils.entryptPassword(userInput.getPassword()));
        }
        User user = UserUtils.getUser();
        if ((userInput.getUserName() != null) && !user.getUserName().equals(userInput.getUserName())) {
            User query = new User();
            query.setUserName(userInput.getUserName());
            if (userService.getOne(Wrappers.query(query)) != null) {
                return new Result(ResultStatus.USER_ALREADY_EXIST, "修改失败");
            }
        }
        if ((userInput.getEmail() != null) && !user.getEmail().equals(userInput.getEmail())) {
            User query = new User();
            query.setEmail(userInput.getEmail());
            if (userService.getOne(Wrappers.query(query)) != null) {
                return new Result(ResultStatus.EMAIL_ALREADY_EXIST, "修改失败");
            }
        }
        if ((userInput.getPhoneNumber() != null)&& !user.getPhoneNumber().equals(userInput.getPhoneNumber())) {
            User query = new User();
            query.setPhoneNumber(userInput.getPhoneNumber());
            if (userService.getOne(Wrappers.query(query)) != null) {
                return new Result(ResultStatus.PHONENUMBER_ALREADY_EXIST, "修改失败");
            }
        }
        boolean b = this.userService.update(userInput, Wrappers.query(user));
        if (b == false) {
            return new Result(ResultStatus.FAIL, "修改失败");
        }
        return Result.ofSuccess("修改成功");


    }

}