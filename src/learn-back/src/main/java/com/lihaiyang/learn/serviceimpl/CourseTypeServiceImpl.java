package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.CourseTypeDao;
import com.lihaiyang.learn.entity.CourseType;
import com.lihaiyang.learn.service.ICourseTypeService;
import org.springframework.stereotype.Service;


@Service
public class CourseTypeServiceImpl extends BaseServiceImpl<CourseTypeDao, CourseType> implements ICourseTypeService {
}
