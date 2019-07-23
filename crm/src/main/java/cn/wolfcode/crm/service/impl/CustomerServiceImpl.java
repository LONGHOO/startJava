package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.mapper.CustomerMapper;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public void saveOrUpdate(Customer entity) {
        if(entity.getId()!=null){
            customerMapper.updateByPrimaryKey(entity);
        }else{
            customerMapper.insert(entity);
        }
    }

    @Override
    public List<Customer> list() {
        return customerMapper.selectAll();
    }


    @Override
    public PageInfo<Customer> query(CustomerQueryObject qo) {
        qo.setStatus(Customer.STATUS_PATENTIAL);
        Page<Object> page = PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Customer> customers = customerMapper.queryByQueryObject(qo);
        return new PageInfo<>(customers);
    }

    @Override
    public PageInfo<Customer> queryPooled(CustomerQueryObject qo) {
        qo.setStatus(Customer.STATUS_POOLED);
        Page<Object> page = PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Customer> customers = customerMapper.queryByQueryObject(qo);
        return new PageInfo<>(customers);
    }

    @Override
    public void updateStatus(Integer status,String customerId) {
        customerMapper.updateStatus(status,customerId);
    }
}
