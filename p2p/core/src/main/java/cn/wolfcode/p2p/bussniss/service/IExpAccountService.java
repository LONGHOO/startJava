package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.bussniss.domain.ExpAcount;

/**
 * @Author: 十一
 * @Date: 2019-06-25 13:17
 * @Descrption
 **/
public interface IExpAccountService {

    void addExpAccount(ExpAcount account);

    void update(ExpAcount account);

    ExpAcount getCureent();

    ExpAcount get(Long accountId);

}
