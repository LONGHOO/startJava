package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.base.domain.Account;
import cn.wolfcode.p2p.bussniss.domain.AccountFlow;
import cn.wolfcode.p2p.bussniss.mapper.AccountFlowMapper;
import cn.wolfcode.p2p.bussniss.service.IAccountFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-21 20:03
 * @Descrption
 **/
@Service
@Transactional
public class AccountFlowService implements IAccountFlowService {

    @Autowired
    private AccountFlowMapper accountFlowMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(AccountFlow record) {
        return accountFlowMapper.insert(record);
    }

    @Override
    public AccountFlow selectByPrimaryKey(Long id) {
        return accountFlowMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AccountFlow> selectAll() {
        return accountFlowMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(AccountFlow record) {
        return accountFlowMapper.updateByPrimaryKey(record);
    }

    @Override
    public void saveAccountFlow(BigDecimal amount, Account account, String note, int actionType) {
        AccountFlow accountFlow = new AccountFlow();
        accountFlow.setFreezedAmount(account.getFreezedAmount());
        accountFlow.setNote(note);
        accountFlow.setUsableAmount(account.getUsableAmount());
        accountFlow.setAccountId(account.getId());
        accountFlow.setActionTime(new Date());
        accountFlow.setAmount(amount);
        accountFlow.setActionType(actionType);
        accountFlowMapper.insert(accountFlow);
    }

    public void saveOrUpdate(AccountFlow accountFlow){
        if(accountFlow.getId() == null){
            this.insert(accountFlow);
        }else{
            this.updateByPrimaryKey(accountFlow);
        }
    }
}
