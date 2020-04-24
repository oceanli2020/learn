package com.lihaiyang.learn.servlet;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.cache.ReplayPools;
import com.lihaiyang.learn.core.utils.RandomUtils;
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
import java.util.Date;

@Component
public class Scheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss");

    @Autowired
    private IReplayService replayService;

    @Autowired
    private ILiveService liveService;

    @Scheduled(initialDelay = 1000 * 30, fixedDelay = 1000 * 30)
    public void saveReplayInfo() throws Exception {
        System.out.println("执行存入直播回放时间：" + dateFormat.format(new Date()));
        File file = new File("G:/replay");
        File[] folders = file.listFiles();
        for (File folder : folders) {
            String code = folder.getName();
            Live query = new Live();
            query.setPushCode(code);
            Long liveId = liveService.getOne(Wrappers.query(query)).getId();
            Integer key = ReplayPools.get(liveId);
            if (key == null) {
                key = 0;
                ReplayPools.put(liveId, key);
            }
            System.out.println(key);
            File[] files = folder.listFiles();
            for (int i = key; i < files.length; i++) {
                File f = files[i];
                String name = f.getName();
                Long duration;
                Date date;
                try {
                    duration = VideoUtils.getDuration(f);
                    String[] strings = name.split("-");
                    int index = strings[0].length() + strings[1].length() + 2;
                    String time = name.substring(index).replace(".mp4", "");
                    date = dateFormat.parse(time);
                } catch (Exception e) {
                    throw new Exception("错误");
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
                entity.setPath(f.getPath().replace("G:", "").replace("\\", "/"));
                replayService.save(entity);
            }
            ReplayPools.put(liveId, folder.listFiles().length);
        }


    }

    @Scheduled(cron = "15 24 01 ? * *")
    public void deleteReplayInfo() {
        System.out.println("执行清除直播回放时间：" + dateFormat.format(new Date()));
        File file = new File("G:/replay");
        File[] folders = file.listFiles();
        for (File folder : folders) {
            String code = folder.getName();
            Live query = new Live();
            query.setPushCode(code);
            Long liveId = liveService.getOne(Wrappers.query(query)).getId();
            File[] files = folder.listFiles();
            for (int i = files.length - 1; i >= 0; i--) {
                if(i<files.length-10){
                    File f = files[i];
                    String path = f.getPath().replace("G:", "").replace("\\", "/");
                    Replay replay = new Replay();
                    replay.setPath(path);
                    replayService.removeById(replayService.getOne(Wrappers.query(replay)).getId());
                    boolean result = f.delete();
                    int tryCount = 0;
                    while (!result && tryCount++ < 100) {
                        System.gc(); // 回收资源
                        result = f.delete();
                    }
                }
            }
            ReplayPools.put(liveId, folder.listFiles().length);
        }

    }
}
