package com.lihaiyang.learn.core.base.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lihaiyang.learn.core.base.entity.BaseEntity;


public interface IBaseService<T extends BaseEntity> extends IService<T> {

    IPage<T> page(Page<T> page, T entity, String sortSql);

}
