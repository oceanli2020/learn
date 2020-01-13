package com.lihaiyang.learn.dao;

import com.lihaiyang.learn.core.dao.BaseDao;
import com.lihaiyang.learn.entity.Demo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemoDao extends BaseDao<Demo> {

    List<Demo> selectByDemoId(@Param("id")Long id);
}
