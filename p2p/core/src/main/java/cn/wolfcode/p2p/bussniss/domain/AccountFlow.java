package cn.wolfcode.p2p.bussniss.domain;

import cn.wolfcode.p2p.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AccountFlow extends BaseDomain {
    private Date actionTime;//流水时间
    private BigDecimal amount;//发生的金额
    private int actionType;//流水类型
    private Long accountId;//用户账户Id
    private String note;//备注信息
    private BigDecimal usableAmount;//变化后的可用金额
    private BigDecimal freezedAmount;//变化后的冻结金额

}