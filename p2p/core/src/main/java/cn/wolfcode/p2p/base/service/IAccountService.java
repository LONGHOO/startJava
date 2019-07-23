package cn.wolfcode.p2p.base.service;

import cn.wolfcode.p2p.base.domain.Account;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
public interface IAccountService {

    void saveOrUpdate(Account account);

    Account getCurrent();
    Account get(Long id);

    void insert(Account account);
}
