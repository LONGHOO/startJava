package cn.wolfcode.p2p.bussniss.domain;

import cn.wolfcode.p2p.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
//系统账户流水信息
@Setter
@Getter
public class SystemAccountFlow extends BaseDomain {
    private Date actionTime;//交易时间
    private BigDecimal amount;//交易金额
    private int actionType;//交易类型
    private String note;//备注
    private BigDecimal usableAmount;//交易后的可用金额
    private BigDecimal freezeAmount;//交易后的冻结金额
}