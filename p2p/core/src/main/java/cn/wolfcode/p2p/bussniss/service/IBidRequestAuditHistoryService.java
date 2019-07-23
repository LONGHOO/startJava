package cn.wolfcode.p2p.bussniss.service;




import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.domain.BidRequestAuditHistory;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface IBidRequestAuditHistoryService {
    void save(BidRequestAuditHistory h);
    /**
     * 创建审核历史对象
     * @param br
     * @param remark
     * @param state
     */
    void createHistory(BidRequest br, String remark, Integer state);

    /**
     * 根据bidRequestId查询该借款的审核历史集合
     * @param bidRequestId
     * @return
     */
    List<BidRequestAuditHistory> queryListByBidRequestId(Long bidRequestId);

    PageInfo<BidRequestAuditHistory> query(QueryObject qo);

    void bidrequestPublishaudit(Long id, Integer state, Date publishTime, String remark);

    public void addAuditHistory(int state, String remark, BidRequest bidRequest, int auditType);
}
