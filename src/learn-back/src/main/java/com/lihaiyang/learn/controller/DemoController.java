package com.lihaiyang.learn.controller;


import com.lihaiyang.learn.entity.Demo;
import com.lihaiyang.learn.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("${adminPath}/demo")
public class DemoController {


    @Autowired
    IDemoService demoService;

    @GetMapping("/{id}")
    public List<Demo> list(@PathVariable("id") Long id)//Controller的传参方式和参数命名？？？
    {
        return demoService.getByDemoId(id);
    }

}
