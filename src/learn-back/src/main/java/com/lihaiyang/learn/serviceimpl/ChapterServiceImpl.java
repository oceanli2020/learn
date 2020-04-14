package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.ChapterDao;
import com.lihaiyang.learn.entity.Chapter;
import com.lihaiyang.learn.service.IChapterCourse;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl extends BaseServiceImpl<ChapterDao, Chapter> implements IChapterCourse {
}
