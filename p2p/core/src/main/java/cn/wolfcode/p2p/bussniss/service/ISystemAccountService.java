package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.bussniss.domain.SystemAccount;

/**
 * @Author: 十一
 * @Date: 2019-06-21 21:07
 * @Descrption
 **/
public interface ISystemAccountService {

    void saveOrUpdate(SystemAccount systemAccount);

    boolean existAccount();

    void update(SystemAccount account);

    SystemAccount getSystemAccount();

}
