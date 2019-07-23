package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.query.BidRequestQueryObject;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-06-19 18:23
 * @Descrption
 **/
public interface IBidRequsetService {

    void saveOrUpdate(BidRequest bidRequest);

    BidRequest getByPrimaryKey(Long id);

    PageInfo<BidRequest> query(BidRequestQueryObject qo);

    void borrowApply(BidRequest bidRequest, int bidrequestTypePublish);

    List<BidRequest> getListFromOneHourLater(Date hour);

    void borrowBid(Long bidRequestId, BigDecimal amount);

    void bidrequestAudit1(Long id, int state, String remark);

    void bidrequestAudit2(Long id, int state, String remark);

    void returnMoney(Long id, int accountActiontypeReturnMoney);
}
