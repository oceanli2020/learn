package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.LiveDao;
import com.lihaiyang.learn.entity.Live;
import com.lihaiyang.learn.service.ILiveService;
import org.springframework.stereotype.Service;

@Service
public class LiveServiceImpl extends BaseServiceImpl<LiveDao, Live> implements ILiveService {
}
