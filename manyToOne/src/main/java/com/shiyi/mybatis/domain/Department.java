package com.shiyi.mybatis.domain;

import lombok.*;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 21:39
 * @Descrption
 **/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Long id;
    private String name;
    private List<Employee> emps;
}
