package com.shiyi.mybatis.domain;

import lombok.*;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-09 20:57
 * @Descrption
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long id;
    private String name;
    private BigDecimal salary;
    private String sn;
    private Long deptId;
    private Department dept;

}
