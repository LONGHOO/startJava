package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.bussniss.domain.Bid;
import cn.wolfcode.p2p.bussniss.mapper.BidMapper;
import cn.wolfcode.p2p.bussniss.service.IBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 十一
 * @Date: 2019-06-21 21:08
 * @Descrption
 **/
@Service
@Transactional
public class BidServiceImpl implements IBidService {

    @Autowired
    private BidMapper bidMapper;
    @Override
    public void saveOrUpdate(Bid bid) {
        if(bid.getId() == null){
            bidMapper.insert(bid);
        }else{
            bidMapper.updateByPrimaryKey(bid);
        }
    }

    @Override
    public void changeStatusByBidRequsetId(Long bidRequestId, int state) {
        bidMapper.changeStatusByBidRequsetId(bidRequestId,state);
    }
}
