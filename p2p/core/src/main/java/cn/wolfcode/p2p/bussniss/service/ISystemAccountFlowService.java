package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.bussniss.domain.SystemAccount;
import cn.wolfcode.p2p.bussniss.domain.SystemAccountFlow;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-06-19 18:23
 * @Descrption
 **/
public interface ISystemAccountFlowService {

    void save(SystemAccountFlow flow);

    void addFlowDetail(BigDecimal amount, int type, String note, SystemAccount systemAccount);
}
