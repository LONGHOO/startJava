package cn.wolfcode.crud.query;

import cn.wolfcode.crud.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class CustomerChartQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    //给定一个默认的分组类型
    private String groupType = "seller.name";

    public String getGroupTypeName(){
        String name = "";
        if ("seller.name".equals(groupType)){
            name = "员工";
        }else if ("DATE_FORMAT(c.input_time,'%Y')".equals(groupType)){
            name = "年";
        }else if ("DATE_FORMAT(c.input_time,'%Y-%m')".equals(groupType)){
            name = "月";
        }else{
            name = "日";
        }
        return name;
    }

    public Date getEndDate(){
        return DateUtil.getEndDate(endDate);
    }
}
