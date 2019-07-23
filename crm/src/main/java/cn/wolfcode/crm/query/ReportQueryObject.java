package cn.wolfcode.crm.query;

import cn.wolfcode.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-05-10 19:55
 * @Descrption
 **/
@Setter
@Getter
public class ReportQueryObject extends QueryObject {

    private String groupType = "e.name";


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    public String getGroupName(){
        String name = "";
        if(groupType.equals("e.name")){
            name = "员工";
        }else if(groupType.equals("DATE_FORMAT(c.input_time,'%Y')")){
            name = "年";
        }else if(groupType.equals("DATE_FORMAT(c.input_time,'%Y-%m')")){
            name="月";
        }else {
            name = "日";
        }
        return name;
    }

    public Date getEndDate(){
       return  DateUtil.getEndDate(endDate);
    }

}
