package cn.wolfcode.crm.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Customer extends BaseDomain {

    //潜在客户
    public static final Integer STATUS_PATENTIAL = 0;
    //客户池
    public static final Integer STATUS_POOLED = 1;
    //失败客户
    public static final Integer STATUS_FAILED = 2;
    //正式客户
    public static final Integer STATUS_NORMAL = 3;
    //流失客户
    public static final Integer STATUS_LOSSED = 4;

    private String name;

    private Integer age;

    private Integer gender;

    private String tel;

    private String qq;

    private SystemDictionaryItem job;

    private SystemDictionaryItem source;

    private Employee seller;

    private Long inputUserId;

    @JsonFormat(pattern = "yyyy-MM-dd",locale = "GMT+8")
    private Date inputTime;

    private Integer status = Customer.STATUS_PATENTIAL;

    public String getStatusName() {
        switch (this.status) {
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

    @Override
    public String getJson() {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("gender", gender);
        map.put("age",age);
        map.put("tel",tel);
        map.put("qq",qq);
        map.put("status",status);
        map.put("job", job.getId());
        map.put("source",source.getId());
        map.put("seller",seller.getId());
        map.put("sellerName",seller.getName());
        return JSON.toJSONString(map);
    }
}