package cn.wolfcode.p2p.base.domain;

import cn.wolfcode.p2p.base.util.Constants;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Account extends BaseDomain{
    private int version;//乐观锁
    private String tradePassword;//交易密码
    private BigDecimal usableAmount = Constants.ZERO;//可用金额
    private BigDecimal freezedAmount = Constants.ZERO;//冻结金额
    private BigDecimal unReceiveInterest = Constants.ZERO;//待收利息
    private BigDecimal unReceivePrincipal = Constants.ZERO;//待收本金
    private BigDecimal unReturnAmount = Constants.ZERO;//待还金额
    private BigDecimal remainBorrowLimit = Constants.BORROWLIMIT;//剩余授信额度
    private BigDecimal borrowLimitAmount = Constants.BORROWLIMIT;//授信额度

    public BigDecimal getTotalAccount(){
        return usableAmount.add(freezedAmount).add(unReceiveInterest);
    }
}