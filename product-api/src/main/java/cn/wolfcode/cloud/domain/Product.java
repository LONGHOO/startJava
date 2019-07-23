package cn.wolfcode.cloud.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: 十一
 * @Date: 2019-07-09 17:10
 * @Descrption
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    private Long id;//商品id
    private String name;//商品名称
    private Integer price;//商品价格
    private int stock;//商品库存
}
