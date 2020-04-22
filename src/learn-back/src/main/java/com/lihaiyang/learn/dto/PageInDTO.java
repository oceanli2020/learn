package com.lihaiyang.learn.dto;

import lombok.Data;

@Data
public class PageInDTO {

    private Long size;

    private Long start;

    private String sortSql;

    private Long userId;
}
