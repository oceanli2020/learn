package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.dao.VideoDao;
import com.lihaiyang.learn.dto.PageInDTO;
import com.lihaiyang.learn.entity.UserVideo;
import com.lihaiyang.learn.entity.Video;
import com.lihaiyang.learn.service.IVideoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl extends BaseServiceImpl<VideoDao, Video> implements IVideoService {


    @Override
    public UserVideo getAmountByVideoIdAndUserId(Long videoId, Long userId){
        return baseMapper.selectAmountByVideoIdAndUserId(videoId,userId);
    }

    @Override
    public UserVideo getLikeByVideoIdAndUserId(Long videoId, Long userId){
        return baseMapper.selectLikeByVideoIdAndUserId(videoId,userId);
    }

    @Override
     public Boolean updateLikeByVideoIdAndUserId(UserVideo userVideo){
        return  baseMapper.updateLikeByVideoIdAndUserId(userVideo);
    }

    @Override
    public Boolean saveLikeByVideoIdAndUserId(UserVideo userVideo){
        return baseMapper.insertLikeByVideoIdAndUserId(userVideo);
    }

    @Override
    public String changeLikeByVideoIdAndUserId(Long videoId){
        Boolean b;
        Long userId = UserUtils.getUser().getId();
        UserVideo userVideo = getLikeByVideoIdAndUserId(videoId,userId);
        UserVideo entity = new UserVideo();
        entity.setUserId(userId);
        entity.setVideoId(videoId);
        if(userVideo!=null){
            if(userVideo.getIsLike().equals("1")){
                entity.setIsLike("0");
                b =updateLikeByVideoIdAndUserId(entity);
            }else {
                entity.setIsLike("1");
                b =updateLikeByVideoIdAndUserId(entity);
            }
        }else {
            entity.setIsLike("1");
            entity.setIsCollect("0");
            b =saveLikeByVideoIdAndUserId(entity);
        }
        return  entity.getIsLike();
    }


    @Override
    public String changeCollectByVideoIdAndUserId(Long videoId){
        Boolean b;
        Long userId = UserUtils.getUser().getId();
        UserVideo userVideo = getLikeByVideoIdAndUserId(videoId,userId);
        UserVideo entity = new UserVideo();
        entity.setUserId(userId);
        entity.setVideoId(videoId);
        if(userVideo!=null){
            if(userVideo.getIsCollect().equals("1")){
                entity.setIsCollect("0");
                b =updateLikeByVideoIdAndUserId(entity);
            }else {
                entity.setIsCollect("1");
                b =updateLikeByVideoIdAndUserId(entity);
            }
        }else {
            entity.setIsLike("0");
            entity.setIsCollect("1");
            b =saveLikeByVideoIdAndUserId(entity);
        }
        return  entity.getIsCollect();
    }

    @Override
    public  List<Video> getLikeByUserId(PageInDTO pageInDTO){
        return baseMapper.selectLikeByUserId(pageInDTO);
    }

    @Override
    public Boolean removeCollectByVideoIdsAndUserId(List<Long> videoIds, Long userId){
        return baseMapper.deleteCollectByVideoIdsAndUserId(videoIds,userId);
    }
}
