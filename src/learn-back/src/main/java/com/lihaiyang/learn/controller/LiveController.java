package com.lihaiyang.learn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.core.utils.RandomUtils;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.entity.Live;
import com.lihaiyang.learn.service.ILiveService;
import com.lihaiyang.learn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"${adminPath}/live"})
public class LiveController {

    @Autowired
    private ILiveService liveService;

    @Autowired
    private IUserService userService;

    @Value("${Rtmp}")
    private String Rtmp;

    @GetMapping
    public Result get(@RequestParam(value = "id",required = false) Long id){
        if (id == null) {
            Live query = new Live();
            query.setCreateBy(UserUtils.getUser().getId());
            Live live = liveService.getOne(Wrappers.query(query));
            if(live==null){
                Live entity = new Live();
                String userName = userService.getById(UserUtils.getUser().getId()).getUserName();
                entity.setName(userName+"的直播间");
                entity.setRtmp(Rtmp);
                entity.setPushCode(UserUtils.getUser().getId()+RandomUtils.getCharAndNumber(4));
                liveService.save(entity);
                live = liveService.getOne(Wrappers.query(query));
            }
            return Result.ofSuccess(live);
       }else {
            return Result.ofSuccess(liveService.getById(id));
        }
    }

    @PutMapping
    public Result update(@RequestBody Live live){
        liveService.updateById(live);
        return Result.ofSuccess("保存成功");
    }

    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO){
        Live entity = new Live();
        String isStart = pageDTO.getQueryField("isStart", String.class);
        entity.setIsStart(isStart);
        String input = pageDTO.getQueryField("input", String.class);
        entity.setName(input);
        IPage<Live> listPage = liveService.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        if(input!=null&&input.length()!=0&&listPage.getRecords().size()==0){
            entity.setName(null);
            entity.setUserName(input);
            listPage = liveService.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        }
        return Result.ofSuccess(new ResultList<>(listPage));
    }
}
