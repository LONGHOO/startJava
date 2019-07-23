package cn.wolfcode.p2p.bussniss.domain;

import cn.wolfcode.p2p.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
/**
 *功能描述 体验金账号
 * @author 十一
 * @param null
 * @return
 * @date 2019-06-25 13:17
 */
public class ExpAcount extends BaseDomain {

    private Integer version;

    private BigDecimal usableAmount;

    private BigDecimal freezedAmount;

    private BigDecimal unReturnExpAmount;
}