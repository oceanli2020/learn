package com.lihaiyang.learn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.dto.CommentDTO;
import com.lihaiyang.learn.dto.CommentInfoDTO;
import com.lihaiyang.learn.dto.IsDisplays;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Comment;
import com.lihaiyang.learn.service.ICommentServide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"${adminPath}/comment"})
public class CommentController {

    @Autowired
    private ICommentServide commentServide;

    /**
     * 获取评论总列表·分页
     */
    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO) {
        Comment entity = new Comment();
        Long videoId = pageDTO.getQueryField("videoId", Long.class);
        entity.setVideoId(videoId);
        entity.setParentId(0L);
        IPage<Comment> listPage = commentServide.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        List<Comment> commentList = listPage.getRecords();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentList.forEach(comment -> {
            PageDTO pageChildDTO = new PageDTO();
            pageChildDTO.setCurrent(1L);
            pageChildDTO.setSize(5L);
            pageChildDTO.setSort("-createDate");
            Comment entityChild = new Comment();
            entityChild.setVideoId(videoId);
            entityChild.setParentId(comment.getId());
            IPage<Comment> listChildPage = commentServide.page(pageChildDTO.getPage(), entityChild, pageChildDTO.getSortSql());
            List<Comment> commentChildList = listChildPage.getRecords();
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setComment(comment.getComment());
            commentDTO.setCommentChildList(commentChildList);
            commentDTO.setCreateBy(comment.getCreateBy());
            commentDTO.setPageDTO(pageChildDTO);
            commentDTO.setTotal(listChildPage.getTotal());
            commentDTO.setCreateDate(comment.getCreateDate());
            IsDisplays  displays = new IsDisplays();
            displays.setIsDisplay(false);
            commentDTO.setIsDisplays(displays);
            commentDTOList.add(commentDTO);

        });
        ResultList<CommentDTO> resultList = new ResultList<>();
        resultList.setContent(commentDTOList);
        resultList.setTotalElements(listPage.getTotal());
        return Result.ofSuccess(resultList);

    }

    /**
     * 获取评论列表·分页
     */
    @PostMapping("/child/page")
    public Result childPage(@RequestBody PageDTO pageDTO) {
        Comment entity = new Comment();
        Long parentId = pageDTO.getQueryField("parentId", Long.class);
        entity.setParentId(parentId);
        IPage<Comment> listPage = commentServide.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        return  Result.ofSuccess(new ResultList<>(listPage));

    }

    @PostMapping("/save")
    public Result save(@RequestBody CommentInfoDTO commentInfoDTO){
        Comment comment = new Comment();
        comment.setVideoId(commentInfoDTO.getVideoId());
        comment.setParentId(commentInfoDTO.getParentId());
        comment.setComment(commentInfoDTO.getComment());
        comment.setCreateTo(commentInfoDTO.getCreateTo());
        commentServide.save(comment);
        return  Result.ofSuccess("评论成功");

    }
}
