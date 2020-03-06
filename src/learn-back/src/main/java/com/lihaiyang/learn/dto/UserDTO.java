package com.lihaiyang.learn.dto;



import com.lihaiyang.learn.core.convert.Convert;
import lombok.Data;

@Data
public class UserDTO {


    @Convert
    private String userName;
    @Convert
    private String realName;
    @Convert
    private String email;
    @Convert
    private String phoneNumber;
    @Convert
    private String profilePhoto;


}
