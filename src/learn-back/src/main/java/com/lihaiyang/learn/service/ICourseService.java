package com.lihaiyang.learn.service;

import com.lihaiyang.learn.core.base.service.IBaseService;
import com.lihaiyang.learn.dto.PageInDTO;
import com.lihaiyang.learn.entity.Course;

import java.util.List;

public interface ICourseService  extends IBaseService<Course> {

    Course getAmountByIdAndUserId(Long id,Long userId);

    Boolean saveSubscribeByIdAndUserId(Long id,Long userId);

    Boolean removeSubscribeByIdAndUserId(Long id,Long userId);

    List<Course> getSubscribeByUserId(PageInDTO pageInDTO);
}
