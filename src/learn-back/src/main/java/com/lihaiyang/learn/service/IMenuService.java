package com.lihaiyang.learn.service;

import com.lihaiyang.learn.core.base.service.IBaseService;
import com.lihaiyang.learn.entity.Menu;

import java.util.List;


public interface IMenuService extends IBaseService<Menu> {

    List<Menu> findByUserId(Menu menu);

}