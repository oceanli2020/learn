package com.lihaiyang.learn.controller;


import com.lihaiyang.learn.service.IReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${adminPath}/replay"})
public class ReplayController {

    @Autowired
    private IReplayService replayService;
}
