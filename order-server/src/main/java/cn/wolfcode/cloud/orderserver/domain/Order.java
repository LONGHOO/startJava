package cn.wolfcode.cloud.orderserver.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order implements Serializable {
    private String orderNo;
    private Date createTime;
    private String productName;
    private int productPrice;
    private Long userId;
}