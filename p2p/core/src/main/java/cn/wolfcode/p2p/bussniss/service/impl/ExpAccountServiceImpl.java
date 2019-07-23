package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.base.util.UserContext;
import cn.wolfcode.p2p.bussniss.domain.ExpAcount;
import cn.wolfcode.p2p.bussniss.mapper.ExpAcountMapper;
import cn.wolfcode.p2p.bussniss.service.IExpAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 十一
 * @Date: 2019-06-25 13:20
 * @Descrption
 **/
@Service
@Transactional
public class ExpAccountServiceImpl implements IExpAccountService{

    @Autowired
    private ExpAcountMapper expAcountMapper;

    @Override
    public void addExpAccount(ExpAcount account) {
        expAcountMapper.insert(account);
    }

    @Override
    public void update(ExpAcount account) {
        expAcountMapper.updateByPrimaryKey(account);
    }

    @Override
    public ExpAcount getCureent() {
        return expAcountMapper.selectByPrimaryKey(UserContext.getCurrentUser().getId());
    }

    @Override
    public ExpAcount get(Long accountId) {
        return expAcountMapper.selectByPrimaryKey(accountId);
    }
}
