package com.lihaiyang.learn.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lihaiyang.learn.core.base.entity.TreeEntity;
import lombok.Data;


@TableName("sys_menu")
@Data
public class Menu extends TreeEntity<Menu> {
    private static final long serialVersionUID = 1L;

    private String name;
    private String href;
    private Long userId;



}
