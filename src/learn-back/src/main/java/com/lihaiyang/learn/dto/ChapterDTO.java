package com.lihaiyang.learn.dto;

import com.lihaiyang.learn.entity.Video;
import lombok.Data;

import java.util.List;

@Data
public class ChapterDTO {

    private Long id;

    private  String name;

    private List<Video> videoList;
}
