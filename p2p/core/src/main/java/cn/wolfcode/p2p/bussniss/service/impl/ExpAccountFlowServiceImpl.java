package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.bussniss.domain.ExpAccountFlow;
import cn.wolfcode.p2p.bussniss.domain.ExpAcount;
import cn.wolfcode.p2p.bussniss.mapper.ExpAccountFlowMapper;
import cn.wolfcode.p2p.bussniss.mapper.ExpAcountMapper;
import cn.wolfcode.p2p.bussniss.service.IExpAccountFlowService;
import cn.wolfcode.p2p.bussniss.service.IExpAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-06-25 13:20
 * @Descrption
 **/
@Service
@Transactional
public class ExpAccountFlowServiceImpl implements IExpAccountFlowService{

    @Autowired
    private ExpAccountFlowMapper flowMapper;

    @Override
    public void addExpAccountFlow(BigDecimal amount, int type, String note, ExpAcount account) {
        ExpAccountFlow expAccountFlow = new ExpAccountFlow();
        expAccountFlow.setActionTime(new Date());
        expAccountFlow.setActionType(type);
        expAccountFlow.setAmount(amount);
        expAccountFlow.setExpAccountId(account.getId());
        expAccountFlow.setFreezedAmount(account.getFreezedAmount());
        expAccountFlow.setUsableAmount(account.getUsableAmount());
        expAccountFlow.setNote(note);
        flowMapper.insert(expAccountFlow);
    }
}
