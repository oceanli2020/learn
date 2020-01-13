package com.lihaiyang.learn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lihaiyang.learn.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("demo")
public class Demo extends BaseEntity<Demo> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;


}
