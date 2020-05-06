package com.lihaiyang.learn.core.utils;


import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultStatus;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtils {

    private static String UploadPath = "D:/upload";

    private static  String ResourcesPath ="D:/resources";

    private  static  String separator ="/";

    public UploadUtils(){

    }


    /**
     * 文件上传
     *
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

    /**
     * 文件下载
     *
     */
    public static  Result downloadFile (String fileName, HttpServletResponse response, HttpServletRequest request){
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取流
            File f = new File(ResourcesPath+separator+fileName);
            is = new FileInputStream(f);
            if (is == null) {
                return new Result(ResultStatus.NO_RESOURCES, "下载失败，请检查文件“" + fileName + "”是否存在");
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return new Result(ResultStatus.IO_EXCEPTION, "下载失败,error:"+e.getMessage());
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {

            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {

            }
        }
        return null;
    }



}
