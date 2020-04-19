package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.VideoDao;
import com.lihaiyang.learn.entity.Video;
import com.lihaiyang.learn.service.IVideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServideImpl extends BaseServiceImpl<VideoDao, Video> implements IVideoService {
}
