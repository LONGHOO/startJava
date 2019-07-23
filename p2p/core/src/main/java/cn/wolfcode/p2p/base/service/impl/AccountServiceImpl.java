package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.Account;
import cn.wolfcode.p2p.base.domain.LoginInfo;
import cn.wolfcode.p2p.base.mapper.AccountMapper;
import cn.wolfcode.p2p.base.service.IAccountService;
import cn.wolfcode.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-06-12 13:43
 * @Descrption
 **/
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void saveOrUpdate(Account account) {
        if(account.getId() != null){
            accountMapper.updateByPrimaryKey(account);
        }else{
            accountMapper.insert(account);
        }
    }

    @Override
    public Account getCurrent() {
        LoginInfo loginInfo = UserContext.getCurrentUser();
        return this.get(loginInfo.getId());
    }

    @Override
    public Account get(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(Account account) {
        accountMapper.insert(account);
    }
}
