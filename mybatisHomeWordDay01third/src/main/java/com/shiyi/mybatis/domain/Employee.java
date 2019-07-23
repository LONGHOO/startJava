package com.shiyi.mybatis.domain;

import lombok.*;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-09 20:57
 * @Descrption
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    /**
     *
     * 功能描述:
     * @param: CREATE TABLE `employee` (
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
     *   `name` varchar(20) DEFAULT NULL,
     *   `sn` varchar(20) DEFAULT NULL,
     *   `salary` decimal(8,2) DEFAULT NULL,
     *   `deptId` bigint(20) DEFAULT NULL,
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
     *
     * INSERT INTO `employee` VALUES ('1', '赵一', '001', '800.00', '10');
     * INSERT INTO `employee` VALUES ('2', '倩儿', '002', '900.00', '10');
     * INSERT INTO `employee` VALUES ('3', '孙三', '003', '800.00', '20');
     * INSERT INTO `employee` VALUES ('4', '李四', '004', '1000.00', '30');
     * INSERT INTO `employee` VALUES ('5', '周五', '005', '900.00', '30');
     * INSERT INTO `employee` VALUES ('6', '吴六', '006', '1200.00', '40');
     * INSERT INTO `employee` VALUES ('7', '郑七', '007', '1400.00', '10');
     * @return:
     * @auther: 十一
     * @date:
     */

    private Long id;
    private String name;
    private BigDecimal salary;
    private String sn;
    private Long deptId;

}
