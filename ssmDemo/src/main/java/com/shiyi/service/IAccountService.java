package com.shiyi.service;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-16 18:52
 * @Descrption
 **/
public interface IAccountService {

    public void transferAccount(Long origin, Long target, BigDecimal balance);
}
