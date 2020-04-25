package com.lihaiyang.learn.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lihaiyang.learn.core.convert.ObjectConvert;
import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultList;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.dto.ChapterDTO;
import com.lihaiyang.learn.dto.CourseDTO;
import com.lihaiyang.learn.dto.PageDTO;
import com.lihaiyang.learn.dto.PageInDTO;
import com.lihaiyang.learn.entity.Chapter;
import com.lihaiyang.learn.entity.Course;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.entity.Video;
import com.lihaiyang.learn.service.IChapterService;
import com.lihaiyang.learn.service.ICourseService;
import com.lihaiyang.learn.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping({"${adminPath}/course"})
public class CourseController {

    @Autowired
    private ICourseService courseService;
    @Autowired
    private IChapterService chapterService;
    @Autowired
    private IVideoService videoService;


    /**
     * 获取课程列表·分页
     */
    @PostMapping("/page")
    public Result page(@RequestBody PageDTO pageDTO) {
        Course entity = new Course();
        Long courseTypeId = pageDTO.getQueryField("courseTypeId", Long.class);
        entity.setCourseTypeId(courseTypeId);
        Boolean isCreateBy = pageDTO.getQueryField("isCreateBy", Boolean.class);
        if (isCreateBy != null) {
            User user = UserUtils.getUser();
            entity.setCreateBy(user.getId());
        }
        String input = pageDTO.getQueryField("input", String.class);
        entity.setName(input);
        IPage<Course> listPage = courseService.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        if(input!=null&&input.length()!=0&&listPage.getRecords().size()==0){
            entity.setName(null);
            entity.setUserName(input);
            listPage = courseService.page(pageDTO.getPage(), entity, pageDTO.getSortSql());
        }
        return Result.ofSuccess(new ResultList<>(listPage));

    }

    /**
     * 获取课程列表
     */
    @GetMapping("/list")
    public Result list() {
        Course query = new Course();
        query.setCreateBy(UserUtils.getUser().getId());
        return Result.ofSuccess(courseService.list(new LambdaQueryWrapper<>(query).orderByAsc(Course::getId)));
    }

    /**
     * 根据课程ID获取课程信息
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        Course course = courseService.getById(id);
        CourseDTO courseDTO = new CourseDTO();
        ObjectConvert<CourseDTO> objectToDto = new ObjectConvert();
        courseDTO = objectToDto.toDto(course, courseDTO);
        course = courseService.getAmountByIdAndUserId(id, UserUtils.getUser().getId());
        courseDTO.setIsSubscribe(course.getIsSubscribe());
        courseDTO.setSubscribeAmount(course.getSubscribeAmount());
        return Result.ofSuccess(courseDTO);
    }


    /**
     * 新建课程
     */
    @PostMapping("/save")
    public Result save(@RequestBody CourseDTO courseDTO) {
        String name = courseDTO.getName();
        Long typeId = courseDTO.getCourseTypeId();
        Course course = new Course();
        course.setCourseTypeId(typeId);
        course.setName(name);
        courseService.save(course);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 修改课程
     */
    @PutMapping
    public Result update(@RequestBody CourseDTO courseDTO) {
        Course entity = new Course();
        entity.setId(courseDTO.getId());
        entity.setIntroduction(courseDTO.getIntroduction());
        courseService.updateById(entity);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 删除课程
     */
    @DeleteMapping
    public Result delete(@RequestParam("id") Long id) {
        Chapter chapterQuery = new Chapter();
        chapterQuery.setCourseId(id);
        List<Chapter> chapterList = chapterService.list(Wrappers.query(chapterQuery));
        if (chapterList.size() != 0) {
            List<Long> chapterIds = new ArrayList<>();
            chapterList.forEach(chapter -> {
                Video videoQuery = new Video();
                videoQuery.setChapterId(chapter.getId());
                List<Video> videoList = videoService.list(Wrappers.query(videoQuery));
                if (videoList.size() != 0) {
                    List<Long> videoIds = new ArrayList<>();
                    videoList.forEach(video -> {
                        videoIds.add(video.getId());
                    });
                    videoService.removeByIds(videoIds);
                }
                chapterIds.add(chapter.getId());
            });
            chapterService.removeByIds(chapterIds);
        }
        courseService.removeById(id);
        return Result.ofSuccess("删除成功");
    }

    @GetMapping("/directory/{id}")
    public Result getDirectory(@PathVariable Long id) {
        Chapter chapterQuery = new Chapter();
        chapterQuery.setCourseId(id);
        List<Chapter> chapterList = chapterService.list(new LambdaQueryWrapper<>(chapterQuery).orderByAsc(Chapter::getId));
        List<ChapterDTO> chapterDTOLIst = new ArrayList<>();
        chapterList.forEach(chapter -> {
            ChapterDTO chapterDTO = new ChapterDTO();
            chapterDTO.setId(chapter.getId());
            chapterDTO.setName(chapter.getName());
            Video videoQuery = new Video();
            videoQuery.setChapterId(chapter.getId());
            List<Video> videoList = videoService.list(new LambdaQueryWrapper<>(videoQuery).orderByAsc(Video::getId));
            chapterDTO.setVideoList(videoList);
            chapterDTOLIst.add(chapterDTO);
        });
        return Result.ofSuccess(chapterDTOLIst);
    }

    @PostMapping("/subscribe/{id}")
    public Result saveSubscribe(@PathVariable Long id) {
        courseService.saveSubscribeByIdAndUserId(id, UserUtils.getUser().getId());
        return Result.ofSuccess("订阅成功");
    }

    @DeleteMapping("/subscribe/{id}")
    public Result removeSubscribe(@PathVariable Long id) {
        courseService.removeSubscribeByIdAndUserId(id, UserUtils.getUser().getId());
        return Result.ofSuccess("取消订阅成功");
    }

    @PostMapping("/subscribe/page")
    public Result getSubscribe(@RequestBody PageDTO pageDTO){
        PageInDTO pageInDTO = new PageInDTO();
        pageInDTO.setUserId(UserUtils.getUser().getId());
        int count = courseService.getSubscribeByUserId(pageInDTO).size();
        pageInDTO.setSize(pageDTO.getSize());
        pageInDTO.setSortSql(pageDTO.getSortSql());
        pageInDTO.setStart((pageDTO.getCurrent()-1) * pageDTO.getSize());
        List<Course> courseList = courseService.getSubscribeByUserId(pageInDTO);
        ResultList resultList = new ResultList();
        resultList.setTotalElements((long)count);
        resultList.setContent(courseList);
        return Result.ofSuccess(resultList);
    }
}
