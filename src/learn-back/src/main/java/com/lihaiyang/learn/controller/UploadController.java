package com.lihaiyang.learn.controller;

import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.utils.UploadUtils;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping({"${adminPath}/upload"})
public class UploadController {


    @Autowired
    private IUserService userService;

    @Value("${UploadPath}")
    private String UploadPath;

    /**
     * 文件上传：头像
     */
    @PostMapping({"/avatar"})
    public Result uploadAvatar(@RequestBody MultipartFile file) {

        String target = "avatar";
        String path = UploadUtils.upload(file,target);
        path = path.replace(UploadPath,"");
        User user = new User();
        user.setId(UserUtils.getUser().getId());
        user.setProfilePhoto(path);
        userService.updateById(user);
        return Result.ofSuccess(path);

    }


    /**
     * 文件上传：视频
     */
    @PostMapping({"/video"})
    public Result uploadVideo(@RequestBody MultipartFile file) {

        String target = "video";
        String path = UploadUtils.upload(file,target);
        path = path.replace(UploadPath,"");
        return Result.ofSuccess(path);

    }

    /**
     * 文件下载
     */
    @GetMapping({"/down"})
    public Result down(@RequestParam("fileName") String fileName, HttpServletResponse response, HttpServletRequest request) {

       return UploadUtils.downloadFile(fileName,response,request);

    }
}
