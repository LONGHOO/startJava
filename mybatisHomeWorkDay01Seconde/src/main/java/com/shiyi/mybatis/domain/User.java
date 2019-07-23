package com.shiyi.mybatis.domain;


import lombok.*;

/**
 * @Author: 十一
 * @Date: 2019-04-09 19:30
 * @Descrption
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Long id;
    private String username;
    private String password;
    private Integer age;

}
