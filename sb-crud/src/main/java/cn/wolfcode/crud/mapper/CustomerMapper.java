package cn.wolfcode.crud.mapper;

import cn.wolfcode.crud.domain.Customer;
import cn.wolfcode.crud.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int updateByPrimaryKey(Customer record);

    List<Customer> selectForList(QueryObject qo);

    void updateStatus(@Param("cid") Long cid, @Param("status") Integer status);

    //潜在客户移交给其他人,然后更新数据
    void updateSellerIdByPrimaryKey(@Param("customerId") Long customerId,
                                    @Param("newSellerId") Long newSellerId);
}