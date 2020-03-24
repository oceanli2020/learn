package com.lihaiyang.learn.dao;

import com.lihaiyang.learn.core.base.dao.BaseDao;
import com.lihaiyang.learn.entity.CourseType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTypeDao extends BaseDao<CourseType> {

    List<CourseType> selectParentsById(@Param("id")Long id);
}
