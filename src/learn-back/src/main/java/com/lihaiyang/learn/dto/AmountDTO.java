package com.lihaiyang.learn.dto;

import lombok.Data;

@Data
public class AmountDTO {

    private Long likeAmount;

    private Long collectAmount;

    private Long shareAmount;

    private Boolean isLike;

    private Boolean isCollect;
}
