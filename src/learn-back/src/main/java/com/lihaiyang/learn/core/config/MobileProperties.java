package com.lihaiyang.learn.core.config;



import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MobileProperties {

    private String jwtSecretKey = "lihaiyang!!!123aabbccffdd";

    private int passwordIterations = 1024;

    private long tokenTimeout =  12000000L;

    private long refreshTokenTimeout = 600000000L;

    private String initialPassword = "123456";
}
