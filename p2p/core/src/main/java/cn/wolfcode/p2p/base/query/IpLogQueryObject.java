package cn.wolfcode.p2p.base.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-06-15 18:29
 * @Descrption
 **/
@Getter
@Setter
public class IpLogQueryObject extends QueryObject {

    private String username;

    private Integer userType = -1;

    private Integer state = -1;

}
