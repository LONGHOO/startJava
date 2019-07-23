package cn.wolfcode.p2p.bussniss.domain;

import cn.wolfcode.p2p.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter
/**
 *功能描述 体验金流水记录
 * @author 十一
 * @param null
 * @return
 * @date 2019-06-25 13:16
 */
public class ExpAccountFlow extends BaseDomain {

    private int actionType;

    private BigDecimal amount;

    private String note;

    private BigDecimal usableAmount;

    private BigDecimal freezedAmount;

    private Date actionTime;

    private Long expAccountId;
}