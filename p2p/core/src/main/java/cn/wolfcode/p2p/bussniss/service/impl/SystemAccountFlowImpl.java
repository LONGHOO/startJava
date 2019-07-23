package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.bussniss.domain.SystemAccount;
import cn.wolfcode.p2p.bussniss.domain.SystemAccountFlow;
import cn.wolfcode.p2p.bussniss.mapper.SystemAccountFlowMapper;
import cn.wolfcode.p2p.bussniss.service.ISystemAccountFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-06-22 18:37
 * @Descrption
 **/
@Service
@Transactional
public class SystemAccountFlowImpl implements ISystemAccountFlowService {

    @Autowired
    private SystemAccountFlowMapper systemAccountFlowMapper;

    @Override
    public void save(SystemAccountFlow flow) {
        systemAccountFlowMapper.insert(flow);
    }

    /**
     *功能描述 保存系统账户流水
     * @author 十一
     * @param principal
     * @param type
     * @param note
     * @param systemAccount
     * @return void
     * @date 2019-06-22 19:28
     */
    @Override
    public void addFlowDetail(BigDecimal amount, int type, String note, SystemAccount systemAccount) {
        SystemAccountFlow flow = new SystemAccountFlow();
        flow.setActionTime(new Date());
        flow.setActionType(type);
        flow.setAmount(amount);
        flow.setFreezeAmount(systemAccount.getFreezedAmount());
        flow.setUsableAmount(systemAccount.getUsableAmount());
        flow.setNote(note);
        systemAccountFlowMapper.insert(flow);
    }
}
