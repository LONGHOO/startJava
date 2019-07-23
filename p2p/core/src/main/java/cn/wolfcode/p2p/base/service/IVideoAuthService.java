package cn.wolfcode.p2p.base.service;

import cn.wolfcode.p2p.base.domain.VideoAuth;
import cn.wolfcode.p2p.base.query.VideoAuthQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-18 19:13
 * @Descrption
 **/
public interface IVideoAuthService {

    int deleteByPrimaryKey(Long id);

    int insert(VideoAuth record);

    VideoAuth selectByPrimaryKey(Long id);

    PageInfo<VideoAuth> selectAll(VideoAuthQueryObject qo);

    int updateByPrimaryKey(VideoAuth record);

    VideoAuth get(Long videoAuthId);

    void videoAuthApply(Long timeId, String orderDate, Long auditorId);

    void videoAuthAudit(VideoAuth videoAuth);
}
