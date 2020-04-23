package com.lihaiyang.learn.core.utils;

import com.coremedia.iso.IsoFile;

import java.io.File;
import java.io.IOException;


public class VideoUtils {


    /**
     * 获取视频文件的播放长度(mp4格式)
     * @return 单位为毫秒
     */
    public static long getDuration(File file) throws IOException {
        IsoFile isoFile = new IsoFile(file.getPath());
        long lengthInSeconds =
                isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                        isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
        return lengthInSeconds;
    }


    /**
     * 得到文件格式
     * @param path
     * @return
     */
    public static String getFormat(String path){
        return  path.toLowerCase().substring(path.toLowerCase().lastIndexOf(".") + 1);
    }


}
