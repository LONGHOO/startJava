package cn.wolfcode.p2p.bussniss.domain;

import cn.wolfcode.p2p.base.domain.BaseDomain;
import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PlatformBankInfo extends BaseDomain {
    private String bankName;//银行名称
    private String accountName;//账户名称
    private String accountNumber;//账户卡号
    private String bankForkname;//分支名称


    public String getJsonString(){
        Map<Object, Object> map = new HashMap<>(16);
        map.put("id",id);
        map.put("bankName",bankName);
        map.put("accountName",accountName);
        map.put("accountNumber",accountNumber);
        map.put("bankForkname",bankForkname);

        return JSONUtils.toJSONString(map);
    }
}