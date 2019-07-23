package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.bussniss.domain.BidRequestAuditHistory;
import java.util.List;

public interface BidRequestAuditHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BidRequestAuditHistory record);

    BidRequestAuditHistory selectByPrimaryKey(Long id);

    List<BidRequestAuditHistory> selectAll();

    int updateByPrimaryKey(BidRequestAuditHistory record);

    List<BidRequestAuditHistory> query(QueryObject qo);

    List<BidRequestAuditHistory> queryListByBidRequestId(Long bidRequestId);
}