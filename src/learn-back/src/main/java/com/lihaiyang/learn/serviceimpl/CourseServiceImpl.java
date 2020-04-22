package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.dao.CourseDao;
import com.lihaiyang.learn.dto.PageInDTO;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.service.ICourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl  extends BaseServiceImpl<CourseDao, Course> implements ICourseService {

    @Override
    public Course getAmountByIdAndUserId(Long id,Long userId){
        return baseMapper.selectAmountByIdAndUserId(id,userId);
    }

    @Override
    public Boolean saveSubscribeByIdAndUserId(Long id,Long userId){
        return  baseMapper.insertSubscribeByIdAndUserId(id,userId);
    }

    @Override
    public Boolean removeSubscribeByIdAndUserId(Long id,Long userId){
        return baseMapper.deleteSubscribeByIdAndUserId(id,userId);
    }

    @Override
    public List<Course> getSubscribeByUserId(PageInDTO pageInDTO){
        return baseMapper.selectSubscribeByUserId(pageInDTO);
    }

}
