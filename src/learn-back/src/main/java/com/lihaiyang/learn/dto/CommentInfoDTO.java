package com.lihaiyang.learn.dto;


import lombok.Data;

@Data
public class CommentInfoDTO {

    private String comment;

    private Long videoId;

    private Long createTo;

    private Long parentId;
}
