package com.lihaiyang.learn.dto;

import lombok.Data;

@Data
public class VideoDTO {

    private Long id;

    private String name;

    private Long courseId;

    private String path;

    private String chapterName;

    private String introduction;

}
