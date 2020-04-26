package com.lihaiyang.learn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lihaiyang.learn.core.convert.Convert;
import com.lihaiyang.learn.entity.Course;
import lombok.Data;

import java.util.Date;

@Data
public class CourseDTO {

    @Convert
    private Long id;

    @Convert
    private String name;

    @Convert
    private Long courseTypeId;

    @Convert
    private  String introduction;

    @Convert
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createDate;

    @Convert
    private Long createBy;

    private Boolean isSubscribe;

    private Long subscribeAmount;

    private String userName;

    private String profilePhoto;
}
