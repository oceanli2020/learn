package com.lihaiyang.learn.servlet;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.utils.RandomUtils;
import com.lihaiyang.learn.core.utils.RedisUtil;
import com.lihaiyang.learn.core.utils.VideoUtils;
import com.lihaiyang.learn.entity.Live;
import com.lihaiyang.learn.entity.Replay;
import com.lihaiyang.learn.service.ILiveService;
import com.lihaiyang.learn.service.IReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class Scheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss");

    @Autowired
    private IReplayService replayService;

    @Autowired
    private ILiveService liveService;

    @Autowired
    private RedisUtil redisUtil;

    @Scheduled(initialDelay = 1000 * 30, fixedDelay = 1000 * 60 * 5)
    public void saveReplayInfo() throws Exception {
        System.out.println("执行存入直播回放时间：" + dateFormat.format(new Date()));
        File file = new File("G:/replay");
        File[] folders = file.listFiles();
        for (File folder : folders) {
            String code = folder.getName();
            Live query = new Live();
            query.setPushCode(code);
            Long liveId = liveService.getOne(Wrappers.query(query)).getId();
            if (!redisUtil.hasKey(liveId.toString())) {
                redisUtil.set(liveId.toString(), 0);
            }
            Integer key = (Integer) redisUtil.get(liveId.toString());
            System.out.println("已录入数据库"+key+"条");
            File[] files = folder.listFiles();
            // 默认是无序的
            orderByDate(files);
            int count = 0 ;
            for (int i = key; i < files.length; i++) {
                File f = files[i];
                String name = f.getName();
                System.out.println(name);
                long duration;
                Date date = new Date();
                try {
                    duration = VideoUtils.getDuration(f);
                    if(duration==0){
                        continue;
                    }
                    String[] strings = name.split("-");
                    int index = strings[0].length() + strings[1].length() + 2;
                    String time = name.substring(index).replace(".mp4", "");
                    // date = formatStringToDateLinux(time);
                    date = dateFormat.parse(time);
                } catch (Exception e) {
                    throw new Exception(e);
                }
                if (duration < 600) {
                    boolean result = f.delete();
                    int tryCount = 0;
                    while (!result && tryCount++ < 100) {
                        System.gc(); // 回收资源
                        result = f.delete();
                    }
                    continue;
                }
                Replay entity = new Replay();
                entity.setName("录像" + liveId + RandomUtils.getCharAndNumber(5));
                entity.setDuration(duration);
                entity.setCreateDate(date);
                entity.setLiveId(liveId);
                entity.setPath(f.getPath().replace("\\", "/").replace("G:", ""));
                replayService.save(entity);
                count++;
            }
            System.out.println("此次录入数据库"+count+"条");
            redisUtil.set(liveId.toString(), key+count);
        }


    }

    public static void orderByDate(File[] fs) {
        Arrays.sort(fs,new Comparator< File>(){
            public int compare(File f1, File f2) {
                long diff = f1.lastModified() - f2.lastModified();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;
            }
            public boolean equals(Object obj) {
                return true;
            }
        });
    }

    public static Date formatStringToDateLinux(String source) throws Exception {
        Date dt = new Date();
        try {
            String[] part1 = source.split("-");
            String[] part2 = part1[3].split("_");
            int years = Integer.parseInt(part1[0]);
            int months = Integer.parseInt(part1[1]);
            int days = Integer.parseInt(part1[2]);
            int hours = Integer.parseInt(part2[0]);
            int minutes = Integer.parseInt(part2[1]);
            int seconds = Integer.parseInt(part2[2]);
            GregorianCalendar gc = new GregorianCalendar(years, months,
                    days, hours, minutes, seconds);
            dt = gc.getTime();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return dt;
    }

}
