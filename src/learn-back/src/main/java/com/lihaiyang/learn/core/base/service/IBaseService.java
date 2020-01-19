package com.lihaiyang.learn.core.base.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lihaiyang.learn.core.base.entity.BaseEntity;


public interface IBaseService<T extends BaseEntity> extends IService<T> {

}
