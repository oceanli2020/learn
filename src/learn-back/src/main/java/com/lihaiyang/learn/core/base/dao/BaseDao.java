package com.lihaiyang.learn.core.base.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lihaiyang.learn.core.base.entity.BaseEntity;


public interface BaseDao<T extends BaseEntity> extends BaseMapper<T> {
}

