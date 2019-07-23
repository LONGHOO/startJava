package cn.wolfcode.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter
public class VideoAuth extends BaseAuthDomain {
    private Date orderBeginDate;//预约开始时间
    private Date orderEndDate;//预约结束时间
}
