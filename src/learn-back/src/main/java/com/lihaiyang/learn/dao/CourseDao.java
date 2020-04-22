package com.lihaiyang.learn.dao;

import com.lihaiyang.learn.core.base.dao.BaseDao;
import com.lihaiyang.learn.dto.PageInDTO;
import com.lihaiyang.learn.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao extends BaseDao<Course> {

    Course selectAmountByIdAndUserId(@Param("id")Long id,@Param("userId")Long userId);

    Boolean insertSubscribeByIdAndUserId(@Param("id")Long id,@Param("userId")Long userId);

    Boolean deleteSubscribeByIdAndUserId(@Param("id")Long id,@Param("userId")Long userId);

    List<Course> selectSubscribeByUserId(PageInDTO pageInDTO);
}
