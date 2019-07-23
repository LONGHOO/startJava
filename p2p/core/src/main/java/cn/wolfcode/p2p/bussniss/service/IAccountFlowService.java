package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.base.domain.Account;
import cn.wolfcode.p2p.bussniss.domain.AccountFlow;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-21 20:03
 * @Descrption
 **/
public interface IAccountFlowService {

    int deleteByPrimaryKey(Long id);

    int insert(AccountFlow record);

    AccountFlow selectByPrimaryKey(Long id);

    List<AccountFlow> selectAll();

    int updateByPrimaryKey(AccountFlow record);

    void saveAccountFlow(BigDecimal amount, Account account, String note, int actionType);
}
