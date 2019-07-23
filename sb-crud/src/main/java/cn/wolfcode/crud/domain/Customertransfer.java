package cn.wolfcode.crud.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter@Setter
public class Customertransfer extends BaseDomain{

    private Customer customer;

    private Employee operator;//当前登录的用户

    private Date operateTime;//当前时间

    private Employee oldseller;

    private Employee newseller;

    private String reason;

}