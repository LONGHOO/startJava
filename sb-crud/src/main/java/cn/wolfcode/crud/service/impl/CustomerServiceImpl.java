package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Customer;
import cn.wolfcode.crud.mapper.CustomerMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ICustomerService;
import cn.wolfcode.crud.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Override

    public void saveOrUpdate(Customer entity) {
        if (entity.getId() == null){
            //封装客户的录入时间和录入人
            entity.setInputUser(UserContext.getCurrentEmp());
            entity.setInputTime(new Date());
            customerMapper.insert(entity);
        }else{
            customerMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void delete(Long id) {
        customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Customer> query(QueryObject qo) {
        //需要分页的sql方法前执行这代码
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Customer> pageList = customerMapper.selectForList(qo);
        return new PageInfo<>(pageList);
    }

    @Override
    public void updateStatus(Long cid, Integer status) {
        customerMapper.updateStatus(cid,status);
    }
}
