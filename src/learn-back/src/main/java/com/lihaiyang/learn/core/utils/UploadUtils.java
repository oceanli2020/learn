package com.lihaiyang.learn.core.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtils {

    private static String UploadPath = "D:/upload";

    private  static  String separator ="/";

    public UploadUtils(){

    }


    /**
     * 文件上传：头像
     */
    public static  String upload(MultipartFile file, String target) {

        //新的文件名
        SimpleDateFormat stFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String newfileName = stFormat.format(new Date());
        // 按年份建立目录
        SimpleDateFormat stFormat1 = new SimpleDateFormat("yyyy");
        String year = stFormat1.format(new Date());
        String path = UploadPath + separator + target + separator + year;
        // 测试绝对路径的目录是否存在，不存在，则建立对应目录;
        File file2 = new File(path);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        // 得到原始的上传文件的文件名（包含后缀）
        String fileName = file.getOriginalFilename();
        // 获得新文件的文件名，添加后缀
        newfileName += fileName.substring(fileName.lastIndexOf("."));
        // 在磁盘建立文件
        File targetFile = new File(path, newfileName);
        try {
            //进行文件复制
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 文件保存的绝对路径
        path += separator + newfileName;
        return path;
    }


}
