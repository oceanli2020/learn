package com.lihaiyang.learn.service;


import com.lihaiyang.learn.core.service.IBaseService;
import com.lihaiyang.learn.entity.Demo;

import java.util.List;

public interface IDemoService extends IBaseService<Demo> {

    List<Demo> getByDemoId(Long id);
}
