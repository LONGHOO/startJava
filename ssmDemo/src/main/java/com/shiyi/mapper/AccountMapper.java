package com.shiyi.mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Author: 十一
 * @Date: 2019-04-16 18:49
 * @Descrption
 **/
public interface AccountMapper {

    public void addBalance(@Param("id") Long id, @Param("balance") BigDecimal balance);

    public void substractBalance(@Param("id") Long id, @Param("balance") BigDecimal balance);
}
