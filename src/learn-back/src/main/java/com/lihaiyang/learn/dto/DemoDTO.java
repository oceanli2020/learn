package com.lihaiyang.learn.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DemoDTO implements Serializable {

    private String demoId;

    private String displayName;

}
