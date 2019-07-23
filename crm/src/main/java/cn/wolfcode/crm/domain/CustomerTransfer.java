package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class CustomerTransfer extends BaseDomain{

    private Customer customer;

    private Employee operator;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date operateTime;

    private Employee newSeller;

    private Employee oldSeller;

    private String reason;


}