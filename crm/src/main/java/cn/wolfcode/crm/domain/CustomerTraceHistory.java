package cn.wolfcode.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class CustomerTraceHistory extends BaseDomain {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date traceTime;

    private String name;

    private String traceDetails;

    private SystemDictionaryItem traceType;

    private Integer traceResult;

    private String remark;

    private Customer customer;

    private Employee inputUser;

    private Date inputTime;

    private Integer type;

    @Override
    public String getJson() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("traceDetails", traceDetails);
        map.put("customerId", customer.getId());
        map.put("inputUserId", inputUser.getId());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(traceTime);
        map.put("traceTime",date);
        map.put("remark",remark);
        map.put("id",id);
        map.put("traceTypeId",traceType.getId());
        map.put("type",type);
        map.put("traceResult", traceResult);
        map.put("customerName", customer.getName());
        return JSON.toJSONString(map);
    }

    public String getStatusName() {
        switch (this.type) {
            case 0:
                return "潜在客户";
            case 1:
                return "客户池";
            case 2:
                return "失败客户";
            case 3:
                return "正式客户";
            default:
                return "流失客户";
        }
    }

}