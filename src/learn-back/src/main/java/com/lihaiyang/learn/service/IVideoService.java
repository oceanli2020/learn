package com.lihaiyang.learn.service;

import com.lihaiyang.learn.core.base.service.IBaseService;
import com.lihaiyang.learn.dto.PageInDTO;
import com.lihaiyang.learn.entity.UserVideo;
import com.lihaiyang.learn.entity.Video;

import java.util.List;

public interface IVideoService extends IBaseService<Video> {

    UserVideo getAmountByVideoIdAndUserId(Long videoId, Long userId);

    UserVideo getLikeByVideoIdAndUserId(Long videoId, Long userId);

    Boolean updateLikeByVideoIdAndUserId(UserVideo userVideo);

    Boolean saveLikeByVideoIdAndUserId(UserVideo userVideo);

    String changeLikeByVideoIdAndUserId(Long videoId);

    String changeCollectByVideoIdAndUserId(Long videoId);

    List<Video> getLikeByUserId(PageInDTO pageInDTO);

}
