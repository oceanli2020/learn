package com.lihaiyang.learn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lihaiyang.learn.entity.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentDTO {

    private Long id;

    private String comment;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    private Date createDate;

    private Long createBy;

    private List<Comment> commentChildList;

    private IsDisplays isDisplays;

    private  PageDTO pageDTO;

    private  Long total;
}


