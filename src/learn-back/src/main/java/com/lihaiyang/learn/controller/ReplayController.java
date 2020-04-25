package com.lihaiyang.learn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.core.utils.RedisUtil;
import com.lihaiyang.learn.core.utils.TimeUtils;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Replay;
import com.lihaiyang.learn.service.IReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping({"${adminPath}/replay"})
public class ReplayController {

    @Autowired
    private IReplayService replayService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO){
        Replay entity = new Replay();
        Long liveId = pageDTO.getQueryField("liveId", Long.class);
        entity.setLiveId(liveId);
        IPage<Replay> listPage = replayService.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        List<Replay> list = listPage.getRecords();
        list.forEach(replay -> {
            replay.setDurationTime(TimeUtils.formatDateTime(replay.getDuration()));
        });
        return Result.ofSuccess(new ResultList<>(listPage));
    }

    @DeleteMapping("/{id}")
    public  Result delete(@PathVariable Long id){
        Replay replay = replayService.getById(id);
        File f = new File("G:"+replay.getPath());
        boolean result = f.delete();
        int tryCount = 0;
        while (!result && tryCount++ < 100) {
            System.gc(); // 回收资源
            result = f.delete();
        }
       redisUtil.set(replay.getLiveId().toString(),(Integer)redisUtil.get(replay.getLiveId().toString())-1);
        replayService.removeById(id);
        return Result.ofSuccess("删除成功");
    }
}
