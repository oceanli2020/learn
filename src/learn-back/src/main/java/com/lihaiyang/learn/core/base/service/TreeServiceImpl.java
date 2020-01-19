package com.lihaiyang.learn.core.base.service;

import com.lihaiyang.learn.core.base.dao.TreeDao;
import com.lihaiyang.learn.core.base.entity.TreeEntity;

public abstract class TreeServiceImpl<D extends TreeDao<T>, T extends TreeEntity<T>> extends BaseServiceImpl<D, T>  {


}
