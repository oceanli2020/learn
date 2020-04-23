package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.ReplayDao;
import com.lihaiyang.learn.entity.Replay;
import com.lihaiyang.learn.service.IReplayService;
import org.springframework.stereotype.Service;

@Service
public class ReplayServiceImpl extends BaseServiceImpl<ReplayDao, Replay> implements IReplayService {
}
