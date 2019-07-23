package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.query.BidRequestQueryObject;

import java.util.Date;
import java.util.List;

public interface BidRequestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BidRequest record);

    BidRequest selectByPrimaryKey(Long id);

    List<BidRequest> selectAll();

    int updateByPrimaryKey(BidRequest record);

    List<BidRequest> query(BidRequestQueryObject qo);

    List<BidRequest> getListFromOneHourLater(Date hour);
}