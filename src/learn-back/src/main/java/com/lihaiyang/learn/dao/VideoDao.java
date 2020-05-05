package com.lihaiyang.learn.dao;

import com.lihaiyang.learn.core.base.dao.BaseDao;
import com.lihaiyang.learn.dto.PageInDTO;
import com.lihaiyang.learn.entity.UserVideo;
import com.lihaiyang.learn.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao  extends BaseDao<Video> {


    UserVideo selectAmountByVideoIdAndUserId(@Param("videoId")Long videoId, @Param("userId")Long userId);

    UserVideo selectLikeByVideoIdAndUserId(@Param("videoId")Long videoId, @Param("userId")Long userId);

    Boolean updateLikeByVideoIdAndUserId(UserVideo userVideo);

    Boolean insertLikeByVideoIdAndUserId(UserVideo userVideo);

    List<Video> selectLikeByUserId(PageInDTO pageInDTO);

    Boolean deleteCollectByVideoIdsAndUserId(@Param("videoIds")List<Long> videoIds, @Param("userId")Long userId);
}
