package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.bussniss.domain.Bid;

/**
 * @Author: 十一
 * @Date: 2019-06-21 21:07
 * @Descrption
 **/
public interface IBidService {

    void saveOrUpdate(Bid bid);

    void changeStatusByBidRequsetId(Long bidRequestId,int state);
}
