package com.lihaiyang.learn.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO {


    private String token;
    private String refreshToken;
    private UserDTO userDTO;


}

