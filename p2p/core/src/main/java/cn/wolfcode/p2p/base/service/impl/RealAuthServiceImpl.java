package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.RealAuth;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.mapper.RealAuthMapper;
import cn.wolfcode.p2p.base.query.RealAuthQueryObject;
import cn.wolfcode.p2p.base.service.IRealAuthService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import cn.wolfcode.p2p.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
@Service
@Transactional
public class RealAuthServiceImpl implements IRealAuthService {

    @Autowired
    private RealAuthMapper realAuthMapper;

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public void saveOrUpdate(RealAuth realAuth) {
        UserInfo info = userInfoService.getCurrent();
        if(BitStateUtil.hasState(info.getBitState(),BitStateUtil.HAS_REALAUTH)){
            throw new DisplayException("您已完成实名认证，不需要重复提交");
        }
        //如果已经在审核状态，则不处理
        if(info.getRealAuthId() == null){
            //设置状态为待审核
            realAuth.setState(RealAuth.STATE_NORMAL);
            realAuth.setApplier(UserContext.getCurrentUser());
            realAuth.setApplyTime(new Date());
            realAuthMapper.insert(realAuth);
            //设置用户信息为审核中
            userInfoService.updateRealAuthState(info.getId(),realAuth.getId());
        }else{
            throw new DisplayException("申请正在审核中，请勿重复提交");
        }

    }

    @Override
    public RealAuth get(Long id) {
        return realAuthMapper.selectByPrimaryKey(id);
    }

    @Override
    public RealAuth getCurrent() {
        return get(userInfoService.getCurrent().getRealAuthId());
    }

    @Override
    public PageInfo<RealAuth> query(RealAuthQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(realAuthMapper.query(qo));
    }

    @Override
    public void audit(RealAuth realAuth) {
        //从数据库中查询出当前实名认证的信息，判断是否存在记录
        RealAuth drealAuth = get(realAuth.getId());
        if(null == drealAuth){
            throw new DisplayException("当前审核记录不存在");
        }
        //判断当前认证状态是带审核状态
        if(RealAuth.STATE_NORMAL != drealAuth.getState()){
            throw new DisplayException("当前认证不能被审核");
        }
        drealAuth.setAuditTime(new Date());
        drealAuth.setAuditor(UserContext.getCurrentUser());
        UserInfo userInfo = userInfoService.get(drealAuth.getApplier().getId());
        drealAuth.setRemark(realAuth.getRemark());
        //如果审核成功，保存审核人的id，审核时间，状态， 保存UserInfo状态的RealAuth状态为1，RealName，idNumber
        if(realAuth.getState() == RealAuth.STATE_PASS){
            drealAuth.setState(RealAuth.STATE_PASS);
            realAuthMapper.updateByPrimaryKey(drealAuth);
            userInfo.setIdNumber(drealAuth.getIdNumber());
            userInfo.setRealName(drealAuth.getRealName());
            //设置状态为已完成实名认证
            userInfo.addState(BitStateUtil.HAS_REALAUTH);
            userInfoService.saveOrUpdate(userInfo);
        }
        //如果审核失败，保存审核人的id，审核时间，状态，设置UserInfo的RealAuthId为null
        if(realAuth.getState() == RealAuth.STATE_REJECT){
            drealAuth.setState(RealAuth.STATE_REJECT);
            realAuthMapper.updateByPrimaryKey(drealAuth);
            userInfo.setRealAuthId(null);
            userInfoService.saveOrUpdate(userInfo);
        }
    }
}
