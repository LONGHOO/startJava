package com.shiyi.mybatis.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-09 21:39
 * @Descrption
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {
    private Long id;
    private String name;
    private List<Teacher> teachers;
}
