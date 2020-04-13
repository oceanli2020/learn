package com.lihaiyang.learn.serviceimpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.CourseTypeDao;
import com.lihaiyang.learn.dto.OptionsDTO;
import com.lihaiyang.learn.entity.CourseType;
import com.lihaiyang.learn.service.ICourseTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseTypeServiceImpl extends BaseServiceImpl<CourseTypeDao, CourseType> implements ICourseTypeService {

    @Override
    public List<CourseType> getParentsById(Long id){
        return  baseMapper.selectParentsById(id);
    }

    @Override
    public List<OptionsDTO> getOptions(){
        CourseType query = new CourseType();
        query.setParentId(0L);
        List<CourseType> courseTypeList = super.list(Wrappers.query(query));
        return recursive(courseTypeList);
    }

    private  List<OptionsDTO>  recursive(List<CourseType> courseTypeList){

        List<OptionsDTO> optionsDTOList = new ArrayList<>();
        courseTypeList.forEach(courseType -> {
            OptionsDTO optionsDTO = new OptionsDTO();
            optionsDTO.setValue(courseType.getId().toString());
            optionsDTO.setLabel(courseType.getName());
            CourseType query = new CourseType();
            query.setParentId(courseType.getId());
            List<OptionsDTO> dtoList = recursive(super.list(Wrappers.query(query)));
            if(dtoList.size()!=0){
                optionsDTO.setChildren(dtoList);
            }
            optionsDTOList.add(optionsDTO);
        });
        return optionsDTOList;
    }
}

