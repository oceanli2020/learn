package com.lihaiyang.learn.service;

import com.lihaiyang.learn.core.base.service.IBaseService;
import com.lihaiyang.learn.entity.CourseType;

import java.util.List;

public interface ICourseTypeService  extends IBaseService<CourseType> {

    List<CourseType> getParentsById(Long id);
}
