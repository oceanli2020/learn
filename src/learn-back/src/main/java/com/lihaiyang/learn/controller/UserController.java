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
    @GetMapping({"checkpass"})
    public Result checkPass(String passwordInput) {
        User user = UserUtils.getUser();
        if(PasswordUtils.validatePassword(passwordInput, user.getPassword())){
            return Result.ofSuccess("密码正确");
        }
        return  new Result(ResultStatus.USER_PASS_EDIT_FAIL,"密码错误");

    }
    /**
     * 用户修改个人信息
     */
    @PutMapping
    public Result update(@RequestBody User userEntity) {
        if(userEntity.getPassword().equals("******")){
            userEntity.setPassword(null);
        }else {
            userEntity.setPassword(PasswordUtils.entryptPassword(userEntity.getPassword()));
        }
        User user = UserUtils.getUser();
        boolean b = this.userService.update(userEntity, Wrappers.query(user));
        if (b == false) {
            return new Result(ResultStatus.FAIL,"修改失败");
        }
        return Result.ofSuccess("修改成功");


    }

}
