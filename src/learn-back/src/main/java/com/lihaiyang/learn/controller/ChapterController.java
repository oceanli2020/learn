package com.lihaiyang.learn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.dto.ChapterDTO;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.entity.Chapter;
import com.lihaiyang.learn.entity.Video;
import com.lihaiyang.learn.service.IChapterService;
import com.lihaiyang.learn.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"${adminPath}/chapter"})
public class ChapterController {

    @Autowired
    private IChapterService chapterCourse;

    @Autowired
    private IVideoService videoService;

    @GetMapping("/{courseId}")
    public Result getByCourseId(@PathVariable Long courseId) {
        Chapter query = new Chapter();
        query.setCourseId(courseId);
        return Result.ofSuccess(chapterCourse.list(new LambdaQueryWrapper<>(query).orderByAsc(Chapter::getId)));
    }

    /**
     * 获取章节列表·分页
     */
    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO) {
        Chapter entity = new Chapter();
        Long courseId = pageDTO.getQueryField("courseId", Long.class);
        entity.setCourseId(courseId);
        IPage<Chapter> listPage = chapterCourse.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        return Result.ofSuccess(new ResultList<>(listPage));

    }

    /**
     * 修改课程
     */
    @PutMapping
    public Result update(@RequestBody ChapterDTO chapterDTO) {
        Chapter entity = new Chapter();
        entity.setId(chapterDTO.getId());
        entity.setName(chapterDTO.getName());
        chapterCourse.updateById(entity);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 删除章节
     */
    @DeleteMapping
    public Result delete(@RequestParam("id") Long id) {
        Video videoQuery = new Video();
        videoQuery.setChapterId(id);
        List<Video> videoList = videoService.list(Wrappers.query(videoQuery));
        if(videoList.size()!=0){
            List<Long> videoIds = new ArrayList<>();
            videoList.forEach(video -> {
                videoIds.add(video.getId());
            });
            videoService.removeByIds(videoIds);
        }
        chapterCourse.removeById(id);
        return Result.ofSuccess("删除成功");

    }
}
