
package com.lihaiyang.learn.core.base.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihaiyang.learn.core.base.entity.BaseEntity;


public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements IBaseService<T> {


    @Override
    public IPage<T> page(Page<T> page, T entity, String sortSql) {
        entity.getSqlMap().put("sortSql", sortSql);
        QueryWrapper<T> queryWrapper = Wrappers.query(entity);
        return super.page(page, queryWrapper);
    }

}
