package com.shiyi.service;

import com.shiyi.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-16 18:53
 * @Descrption
 **/
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper mapper;

    public AccountMapper getMapper() {
        return mapper;
    }

    public void setMapper(AccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferAccount(Long origin, Long target, BigDecimal balance) {
        mapper.substractBalance(origin,balance);
        System.out.println( 1 / 0);
        mapper.addBalance(target,balance);
    }
}
