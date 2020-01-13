package com.lihaiyang.learn.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lihaiyang.learn.core.entity.BaseEntity;

public interface BaseDao<T extends BaseEntity> extends BaseMapper<T> {
}
