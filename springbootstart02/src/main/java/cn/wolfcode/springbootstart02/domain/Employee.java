package cn.wolfcode.springbootstart02.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-06-10 17:15
 * @Descrption
 **/
@Setter
@Getter
public class Employee {

    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin;

}
