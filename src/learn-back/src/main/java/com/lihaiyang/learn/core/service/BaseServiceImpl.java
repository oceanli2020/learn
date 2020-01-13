package com.lihaiyang.learn.core.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihaiyang.learn.core.entity.BaseEntity;

public abstract class BaseServiceImpl<M extends BaseMapper<T>,T extends BaseEntity> extends ServiceImpl<M,T> implements IBaseService<T>  {
}
