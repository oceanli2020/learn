package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.CourseDao;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.service.ICourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl  extends BaseServiceImpl<CourseDao, Course> implements ICourseService {
}
