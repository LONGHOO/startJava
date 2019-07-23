package cn.wolfcode.p2p.bussniss.domain;

import cn.wolfcode.p2p.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
/**
 *功能描述 体验金发放记录
 * @author 十一
 * @param null
 * @return
 * @date 2019-06-25 13:16
 */
public class ExpAccountGrantRecord extends BaseDomain {

    private Long grantUserId;

    private BigDecimal amount;

    private Date grantDate;

    private Date returnDate;

    private Byte grantType;

    private String note;

    private Byte state;
}