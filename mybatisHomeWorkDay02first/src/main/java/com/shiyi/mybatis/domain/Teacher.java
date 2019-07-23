package com.shiyi.mybatis.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-09 20:57
 * @Descrption
 **/
@Data
@AllArgsConstructor
public class Teacher implements Serializable {

    private Long id;
    private String name;

}
