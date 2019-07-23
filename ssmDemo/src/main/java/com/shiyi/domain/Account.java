package com.shiyi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-16 18:44
 * @Descrption
 **/

@Getter
@Setter
@ToString
public class Account {

    private Long id;
    private BigDecimal balance;
}
