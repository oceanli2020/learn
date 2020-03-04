package com.lihaiyang.learn.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import javax.servlet.http.HttpServletRequest;
import com.lihaiyang.learn.core.account.AccountLocals;
import com.lihaiyang.learn.core.convert.ObjectConvert;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultStatus;
import com.lihaiyang.learn.core.utils.PasswordUtils;
import com.lihaiyang.learn.core.utils.TokenUtils;
import com.lihaiyang.learn.core.utils.UserAgentUtils;
import com.lihaiyang.learn.dto.LoginDTO;
import com.lihaiyang.learn.dto.LoginInDTO;
import com.lihaiyang.learn.dto.UserDTO;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.service.ILoginService;
import com.lihaiyang.learn.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录Controller
 * */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ILoginService loginService;

    /**
     * 用户登录
     * */
    @PostMapping({"${adminPath}/login"})
    public Result login(@RequestBody LoginInDTO loginInDTO, HttpServletRequest request) {
        String username = loginInDTO.getUsername();
        String password = loginInDTO.getPassword();
        LoginDTO loginDTO = new LoginDTO();
        User query = new User();
        query.setLoginName(username);
        //根据用户名在数据库查询得到对应用户
        User user = this.userService.getOne(Wrappers.query(query));
        if(user==null) {
            return new Result(ResultStatus.USER_NOT_FOUND, "登陆失败");
        }
        //ThreadLocal
        AccountLocals.setAccount(user);
        //判断输入密码是否正确
        if (PasswordUtils.validatePassword(password, user.getPassword())) {
            //根据用户id，设备类型创建token、refreshToken
            loginDTO.setToken(TokenUtils.createToken(user.getId().toString(), UserAgentUtils.getDeviceType(request).getName()));
            loginDTO.setRefreshToken(TokenUtils.createRefreshToken(user.getId().toString()));
            ObjectConvert<UserDTO> objectToDto = new ObjectConvert();
            UserDTO userDTO = new UserDTO();
            userDTO = (UserDTO)objectToDto.toDto(user, userDTO);
            loginDTO.setUserDTO(userDTO);
            return Result.ofSuccess(loginDTO);
        } else {
            return new Result(ResultStatus.USER_PASS_EDIT_FAIL, "登陆失败");
        }

    }

    /**
     * 用户登出
     * */
    @GetMapping({"/logout"})
    public ResponseEntity<Void> logout() {
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().build();
    }


    /**
     * token刷新
     * */
   @GetMapping({"${adminPath}/token"})
    public Result refreshToken(String refreshToken, HttpServletRequest request) {
       System.out.println(refreshToken);
        String token = this.loginService.getToken(refreshToken, request);
        return Result.ofSuccess(token);
    }




}

