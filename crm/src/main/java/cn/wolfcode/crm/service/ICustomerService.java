package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.query.CustomerQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerService {

    void saveOrUpdate(Customer entity);

    List<Customer> list();

    PageInfo<Customer> query(CustomerQueryObject qo);

    PageInfo<Customer> queryPooled(CustomerQueryObject qo);

    void updateStatus(Integer status,String customerId);
}
