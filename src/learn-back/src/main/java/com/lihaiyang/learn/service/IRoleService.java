package com.lihaiyang.learn.service;


import com.lihaiyang.learn.core.base.service.IBaseService;
import com.lihaiyang.learn.entity.Menu;
import com.lihaiyang.learn.entity.Role;

import java.util.List;


public interface IRoleService extends IBaseService<Role> {

    List<Role> findByUserId(Role role);

}
