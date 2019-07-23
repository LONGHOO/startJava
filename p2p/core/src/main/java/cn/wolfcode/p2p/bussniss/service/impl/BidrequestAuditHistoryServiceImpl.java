package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.UserContext;
import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.domain.BidRequestAuditHistory;
import cn.wolfcode.p2p.bussniss.mapper.BidRequestAuditHistoryMapper;
import cn.wolfcode.p2p.bussniss.service.IBidRequestAuditHistoryService;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import cn.wolfcode.p2p.bussniss.util.CalculatetUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BidrequestAuditHistoryServiceImpl implements IBidRequestAuditHistoryService {

    @Autowired
    private BidRequestAuditHistoryMapper bidrequestAuditHistoryMapper;

    @Autowired
    private IBidRequsetService bidRequsetService;

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public void createHistory(BidRequest br, String remark, Integer state) {

    }

    @Override
    public List<BidRequestAuditHistory> queryListByBidRequestId(Long bidRequestId) {
        return bidrequestAuditHistoryMapper.queryListByBidRequestId(bidRequestId);
    }

    @Override
    public PageInfo<BidRequestAuditHistory> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(bidrequestAuditHistoryMapper.query(qo));
    }

    /**
     * 功能描述 发标前审核
     *
     * @param id
     * @param state
     * @param publishTime
     * @param remark
     * @return void
     * @author 十一
     * @date 2019-06-25 19:15
     */
    @Override
    public void bidrequestPublishaudit(Long id, Integer state, Date publishTime, String remark) {
        BidRequest bidRequest = bidRequsetService.getByPrimaryKey(id);
        //如果当前标为信用标
        UserInfo info = null;
        if (bidRequest.getBidRequestType() == Constants.BIDREQUEST_TYPE_NORMAL) {
            info = userInfoService.get(bidRequest.getCreateUser().getId());
        }
        //标对象是否存在
        if (bidRequest == null) {
            throw new DisplayException("当前标出现异常");
        }
        //是否是发标签待审核
        if (bidRequest.getBidRequestState() != Constants.BIDREQUEST_STATE_APPLY) {
            throw new DisplayException("当前标状态出现异常");
        }
        //标审核历史
        addAuditHistory(state, remark, bidRequest, BidRequestAuditHistory.TYPE_PUBLISH);
        //如果通过审核
        if (state == BidRequestAuditHistory.STATE_PASS) {
            //如果发布时间为null
            if (publishTime == null) {
                //设置标申请对象的状态为招标中
                bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_BIDDING);
                bidRequest.setDisableDate(DateUtils.addDays(new Date(), bidRequest.getDisableDays()));
                //设置发布时间
                bidRequest.setPublishTime(new Date());
            } else {
                bidRequest.setPublishTime(publishTime);
                //设置截止时间为发布时间后加招标天数
                bidRequest.setDisableDate(DateUtils.addDays(publishTime, bidRequest.getDisableDays()));
                bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_PUBLISH_PENDING);
                bidRequest.setPublishTime(publishTime);
            }
            //如果审核被拒绝
        } else {
            bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_PUBLISH_REFUSE);
            if (bidRequest.getBidRequestType() == Constants.BIDREQUEST_TYPE_NORMAL) {
                info.removeState(BitStateUtil.HAS_BIDREQUEST_PROCESS);
            }
        }
        bidRequsetService.saveOrUpdate(bidRequest);
        //保存用户信息
        if (info != null) {
            userInfoService.saveOrUpdate(info);
        }
    }

    @Override
    public void save(BidRequestAuditHistory h) {
        this.bidrequestAuditHistoryMapper.insert(h);
    }


    @Override
    public void addAuditHistory(int state, String remark, BidRequest bidRequest, int auditType) {
        BidRequestAuditHistory history = new BidRequestAuditHistory();
        history.setAuditTime(new Date());
        history.setApplyTime(bidRequest.getApplyTime());
        history.setAuditType(auditType);
        history.setState(state);
        history.setRemark(remark);
        history.setAuditor(UserContext.getCurrentUser());
        history.setApplier(bidRequest.getCreateUser());
        history.setBidRequestId(bidRequest.getId());
        //保存审核信息
        this.save(history);
    }
}
