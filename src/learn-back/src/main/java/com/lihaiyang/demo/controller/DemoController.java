package com.lihaiyang.demo.controller;

import com.lihaiyang.demo.po.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
public class DemoController {


    @PostMapping("login")
    public String test(@RequestBody User user)
    {
        String name="123";
        String pwd="456";
        if(name.equals(user.getUsername())&&pwd.equals(user.getPassword()))
        {
            return"success";
        }
        return "Error....";
    }
}
