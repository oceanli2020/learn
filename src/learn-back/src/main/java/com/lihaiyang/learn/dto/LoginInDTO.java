package com.lihaiyang.learn.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class LoginInDTO implements Serializable {

    private String username;
    private String password;

}
