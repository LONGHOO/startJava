package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.bussniss.domain.SystemAccount;
import cn.wolfcode.p2p.bussniss.mapper.SystemAccountMapper;
import cn.wolfcode.p2p.bussniss.service.ISystemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 十一
 * @Date: 2019-06-22 18:34
 * @Descrption
 **/

@Service
@Transactional
public class SystemAccountImpl implements ISystemAccountService {

    @Autowired
    private SystemAccountMapper systemAccountMapper;

    @Override
    public void saveOrUpdate(SystemAccount systemAccount) {
        if(systemAccount.getId() == null){
            systemAccountMapper.insert(systemAccount);
        }else{
            systemAccountMapper.updateByPrimaryKey(systemAccount);
        }
    }

    @Override
    public boolean existAccount() {
        return systemAccountMapper.existAccount() > 0;
    }

    @Override
    public void update(SystemAccount account) {
        systemAccountMapper.updateByPrimaryKey(account);
    }

    @Override
    public SystemAccount getSystemAccount() {
        return systemAccountMapper.selectAll().get(0);
    }
}
