package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Customer;
import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerService {

    void saveOrUpdate(Customer entity);

    void delete(Long id);

    PageInfo<Customer> query(QueryObject qo);

    void updateStatus(Long cid, Integer status);
}
