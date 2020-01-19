package com.lihaiyang.learn.dao;

import com.lihaiyang.learn.core.base.dao.TreeDao;
import com.lihaiyang.learn.entity.Menu;

import java.util.List;

public interface MenuDao extends TreeDao<Menu> {

    List<Menu> findByUserId(Menu menu);

}
