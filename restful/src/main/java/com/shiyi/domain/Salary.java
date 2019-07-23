package com.shiyi.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 每月工资信息
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Salary {
    private Long id;
    //员工id
    private Long employeeId;
    //工资
    private BigDecimal money;
    //所属时间
    @JsonFormat(pattern = "yyyy-MM")
    private Date date;
}
