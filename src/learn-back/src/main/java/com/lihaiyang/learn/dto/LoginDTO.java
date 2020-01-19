package com.lihaiyang.learn.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {


    private String token;
    private String refreshToken;
    private UserDTO userDTO;


}

