package cn.wolfcode.p2p.bussniss.domain;

import cn.wolfcode.p2p.base.domain.BaseAuthDomain;
import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class RechargeOffline extends BaseAuthDomain {
    private PlatformBankInfo bankInfo;//平台账户
    private String tradeCode;//交易号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rechargeDate;//交易日期
    private BigDecimal amount;//充值金额
    private String note;//备注信息

    public String getJsonString(){
        Map<Object, Object> map = new HashMap<>(16);
        map.put("id",id);
        map.put("username",applier.getUsername());
        map.put("tradeCode",tradeCode);
        map.put("amount",amount);
        map.put("rechargeDate",rechargeDate);
        return JSONUtils.toJSONString(map);
    }
}