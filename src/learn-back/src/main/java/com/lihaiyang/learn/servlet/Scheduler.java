package com.lihaiyang.learn.servlet;

import com.lihaiyang.learn.core.utils.RandomUtils;
import com.lihaiyang.learn.core.utils.VideoUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Scheduled(fixedDelay = 1000 * 10)
    public void testTasks() {
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
        System.out.println("............"+ RandomUtils.getCharAndNumber(4));
//        File file = new File("G:/folder/abcd");
//        File[] files = file.listFiles();
//        System.out.println("文件名\t" + "文件类型\t" + "文件大小\t" + "播放时长\t" + "最后修改时间");
//        for (File f : files) {
//            System.out.print(f.getName() + "\t");
//            if (f.isFile()) {
//                System.out.print("普通文件\t");
//            } else {
//                System.out.print("文件夹\t");
//            }
//            System.out.print(f.length() + "           ");
//            try {
//                System.out.print(VideoUtils.getDuration(f)+ "        ");
//            }catch (Exception e){
//
//            }
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
//            System.out.print(sf.format(new Date(f.lastModified())));
//            System.out.println();
 //       }

    }
}
