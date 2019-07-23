package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-05-10 19:55
 * @Descrption
 **/
@Setter
@Getter
public class CustomerQueryObject extends QueryObject {

    private String keyword;
    private Long sellerId;
    private Integer status;
    private Integer type;
}
