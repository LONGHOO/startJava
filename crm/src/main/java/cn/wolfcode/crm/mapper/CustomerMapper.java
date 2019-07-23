package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.query.CustomerQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    Customer selectByPrimaryKey(Long id);

    List<Customer> selectAll();

    List<Customer> queryByQueryObject(CustomerQueryObject qo);

    int updateByPrimaryKey(Customer record);

    void insert(Customer entity);

    void updateStatus(@Param("status") Integer status, @Param("customerId") String customerId);

    void updateUserSeller(@Param("cusId") Long cusId, @Param("sellerId") Long sellerId);
}
