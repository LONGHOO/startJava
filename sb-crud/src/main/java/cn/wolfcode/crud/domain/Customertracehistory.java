package cn.wolfcode.crud.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Customertracehistory extends BaseDomain{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date traceTime;

    private String traceDetails;

    private Systemdictionaryitem traceType;

    private Integer traceResult;

    private String remark;

    private Customer customer;

    private Employee inputUser;

    private Date inputTime;

    public String getTraceResultName(){
        String resultName = null;
        if (traceResult == 1){
            resultName = "差";
        }else if (traceResult == 2){
            resultName = "中";
        }else{
            resultName = "优";
        }
        return resultName;
    }

    public String getJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        //跟进时间转指定格式时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(traceTime);
        map.put("traceTime",format);
        map.put("customerName",customer.getName());
        map.put("traceDetails",traceDetails);
        map.put("traceTypeId",traceType.getId());//-----如果是空值,有些是数据库脏数据,另外就是封装错误
        map.put("traceResult",traceResult);
        map.put("remark",remark);
        //map集合转json字符串返回
        return JSON.toJSONString(map);
    }

}