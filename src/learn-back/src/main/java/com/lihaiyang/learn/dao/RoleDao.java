package com.lihaiyang.learn.dao;

import com.lihaiyang.learn.core.base.dao.BaseDao;
import com.lihaiyang.learn.entity.Menu;
import com.lihaiyang.learn.entity.Role;

import java.util.List;

public interface RoleDao extends BaseDao<Role> {

    List<Role> findByUserId(Role role);
}
