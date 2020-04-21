package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.CommentDao;
import com.lihaiyang.learn.entity.Comment;
import com.lihaiyang.learn.service.ICommentServide;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentDao, Comment> implements ICommentServide {
}
