package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.LoginInfo;
import cn.wolfcode.p2p.base.domain.OrderTime;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.domain.VideoAuth;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.mapper.VideoAuthMapper;
import cn.wolfcode.p2p.base.query.VideoAuthQueryObject;
import cn.wolfcode.p2p.base.service.IOrderTimeService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.service.IVideoAuthService;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import cn.wolfcode.p2p.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-06-18 19:14
 * @Descrption
 **/
@Service
public class VideoAuthServiceImpl implements IVideoAuthService {

    @Autowired
    private VideoAuthMapper videoAuthMapper;

    @Autowired
    private IOrderTimeService orderTimeService;

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return videoAuthMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VideoAuth record) {
        return videoAuthMapper.insert(record);
    }

    @Override
    public VideoAuth selectByPrimaryKey(Long id) {
        return videoAuthMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<VideoAuth> selectAll(VideoAuthQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(videoAuthMapper.selectAll(qo));
    }

    @Override
    public int updateByPrimaryKey(VideoAuth record) {
        return videoAuthMapper.updateByPrimaryKey(record);
    }

    @Override
    public VideoAuth get(Long videoAuthId) {
        return videoAuthMapper.get(videoAuthId);
    }

    @Override
    public void videoAuthApply(Long timeid, String orderDate, Long auditorId) {
        //获取当前的时间对象
        OrderTime time = orderTimeService.selectByPrimaryKey(timeid);
        String beginTime = orderDate + " " + time.getBegin();
        String endTime = orderDate + " " + time.getEnd();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        VideoAuth videoAuth = new VideoAuth();
        try {
            videoAuth.setOrderBeginDate(sdf.parse(beginTime));
            videoAuth.setOrderEndDate(sdf.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new DisplayException("日期格式不正确");
        }
        videoAuth.setApplier(UserContext.getCurrentUser());
        videoAuth.setApplyTime(new Date());
        //设置状态为待审核
        videoAuth.setState(VideoAuth.STATE_NORMAL);
        //设置预约客服
        LoginInfo auditor = new LoginInfo();
        auditor.setId(auditorId);
        videoAuth.setAuditor(auditor);
        videoAuthMapper.insert(videoAuth);
        UserInfo info = userInfoService.getCurrent();
        info.setVideoAuthId(videoAuth.getId());
        userInfoService.saveOrUpdate(info);
    }

    @Override
    public void videoAuthAudit(VideoAuth videoAuth) {
        VideoAuth auth = videoAuthMapper.get(videoAuth.getId());
        if(auth != null && auth.getState() == VideoAuth.STATE_NORMAL){
            auth.setAuditor(UserContext.getCurrentUser());
            auth.setAuditTime(new Date());
            auth.setRemark(videoAuth.getRemark());
            UserInfo info = userInfoService.get(auth.getApplier().getId());
            if(videoAuth.getState()==VideoAuth.STATE_PASS){
                auth.setState(VideoAuth.STATE_PASS);
                info.addState(BitStateUtil.HAS_VIDEOAUTH);
            }else{
                auth.setState(VideoAuth.STATE_REJECT);
                info.setVideoAuthId(null);
            }
            videoAuthMapper.updateByPrimaryKey(auth);
            userInfoService.saveOrUpdate(info);
        }else{
            throw new DisplayException("当前认证记录存在异常，请联系管理员");
        }

    }
}
