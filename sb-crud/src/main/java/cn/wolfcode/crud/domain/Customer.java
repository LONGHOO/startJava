package cn.wolfcode.crud.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter@Setter
@ToString
public class Customer extends BaseDomain{

    private String name;

    private Integer age;

    private Integer gender;

    private String tel;

    private String qq;

    private Systemdictionaryitem job;

    private Systemdictionaryitem source;

    private Employee seller;

    private Employee inputUser;

    private Date inputTime;

    public static final Integer STATUS_POTENTLAL = 0;//潜在客户
    public static final Integer STATUS_POOLED = 1;//客户池客户
    public static final Integer STATUS_NORMAL = 2;//正式客户
    public static final Integer STATUS_FAILED = 3;//失败客户
    public static final Integer STATUS_LOSSED = 4;//流失客户

    private Integer status = STATUS_POTENTLAL;

    public String getStatusName(){
        if (status==0){
            return "潜在客户";
        }else if (status==1){
            return "客户池客户";
        }else if (status==2){
            return "正式客户";
        }else if (status==3){
            return "失败客户";
        }else if (status==4){
            return "流失客户";
        }
        return null;
    }

    public String getJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("age",age);
        map.put("gender",gender);
        map.put("tel",tel);
        map.put("qq",qq);
        map.put("jobId",job.getId());
        map.put("sourceId",source.getId());
        map.put("sellerId",seller.getId());//---------空值是因为数据库脏数据或者是封装问题(别名错误等等)
        map.put("sellerName",seller.getName());
        //map集合转json字符串返回
        return JSON.toJSONString(map);
    }
}