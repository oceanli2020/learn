package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.DemoDao;
import com.lihaiyang.learn.entity.Demo;
import com.lihaiyang.learn.service.IDemoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl extends BaseServiceImpl<DemoDao, Demo> implements IDemoService {


    @Override
    public List<Demo> getByDemoId(Long id) {
        return baseMapper.selectByDemoId(id);
    }
}
