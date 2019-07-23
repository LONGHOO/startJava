package cn.wolfcode.crud.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PotentialCustomerQueryObject extends QueryObject {
    private Integer status;

    private Long sellerId = -1L;//销售人员id
}
