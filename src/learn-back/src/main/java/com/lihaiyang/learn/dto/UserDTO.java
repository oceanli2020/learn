package com.lihaiyang.learn.dto;



import com.lihaiyang.learn.core.convert.Convert;
import lombok.Data;

@Data
public class UserDTO {

    @Convert
    private Long id;
    @Convert
    private String loginName;
    @Convert
    private String password;
    @Convert
    private String name;
    @Convert
    private String email;
    @Convert
    private String phone;
    @Convert
    private String photo;

    private String roleName;

}
