package com.lihaiyang.learn.core.base.entity;


import lombok.Data;


@Data
public abstract class TreeEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    protected Long parentId;
    protected String parentIds;


}
