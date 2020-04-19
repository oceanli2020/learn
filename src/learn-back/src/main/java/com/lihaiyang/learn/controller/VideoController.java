package com.lihaiyang.learn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.dto.VideoDTO;
import com.lihaiyang.learn.entity.Chapter;
import com.lihaiyang.learn.entity.Video;
import com.lihaiyang.learn.service.IChapterService;
import com.lihaiyang.learn.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"${adminPath}/video"})
public class VideoController {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IChapterService chapterService;


    @PostMapping("/save")
    public Result getByCourseId(@RequestBody VideoDTO videoDTO) {
        Chapter chapter = new Chapter();
        chapter.setCourseId(videoDTO.getCourseId());
        chapter.setName(videoDTO.getChapterName());
        Chapter chapter1 = chapterService.getOne(Wrappers.query(chapter));
        Long chapterId;
        if (chapter1 == null) {
            chapterService.save(chapter);
            chapterId = chapter.getId();
        }else {
            chapterId = chapter1.getId();
        }
        Video video = new Video();
        video.setChapterId(chapterId);
        video.setName(videoDTO.getName());
        video.setPath(videoDTO.getPath());
        video.setIntroduction(videoDTO.getIntroduction());
        videoService.save(video);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 获取视频列表·分页
     * */
    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO) {
        Video entity = new Video();
        Long chapterId = pageDTO.getQueryField("chapterId", Long.class);
        entity.setChapterId(chapterId);
        IPage<Video> listPage = videoService.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        return Result.ofSuccess(new ResultList<>(listPage));

    }

    /**
     * 修改视频
     * */
    @PutMapping
    public  Result update(@RequestBody VideoDTO videoDTO){
        Video entity = new Video();
        entity.setId(videoDTO.getId());
        entity.setName(videoDTO.getName());
        entity.setIntroduction(videoDTO.getIntroduction());
        Chapter chapter = new Chapter();
        chapter.setCourseId(videoDTO.getCourseId());
        chapter .setName(videoDTO.getChapterName());
        Chapter chapter1 = chapterService.getOne(Wrappers.query(chapter));
        Long chapterId;
        if (chapter1 == null) {
            chapterService.save(chapter);
            chapterId = chapter.getId();
        }else {
            chapterId = chapter1.getId();
        }
        entity.setChapterId(chapterId);
        videoService.updateById(entity);
        return  Result.ofSuccess("保存成功");
    }

    /**
     * 删除视频
     * */
    @DeleteMapping
    public  Result delete(@RequestParam("id")Long id){
        videoService.removeById(id);
        return  Result.ofSuccess("删除成功");
    }

}
