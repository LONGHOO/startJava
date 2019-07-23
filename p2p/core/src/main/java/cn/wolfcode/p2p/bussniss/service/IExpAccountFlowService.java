package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.bussniss.domain.ExpAcount;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-06-25 13:17
 * @Descrption
 **/
public interface IExpAccountFlowService {

    void addExpAccountFlow(BigDecimal amount,int type,String note,ExpAcount account);
}
