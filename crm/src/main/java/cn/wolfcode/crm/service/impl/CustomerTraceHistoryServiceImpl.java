package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CustomerTraceHistoryMapper;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.service.ICustomerTraceHistoryService;
import cn.wolfcode.crm.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerTraceHistoryServiceImpl implements ICustomerTraceHistoryService {
    @Autowired
    private CustomerTraceHistoryMapper customerTraceHistoryMapper;


    @Override
    public void saveOrUpdate(CustomerTraceHistory entity) {
        entity.setInputUser(UserContext.getCurrentEmp());
        entity.setInputTime(new Date());
        customerTraceHistoryMapper.insert(entity);
    }

    @Override
    public List<CustomerTraceHistory> list() {
        return customerTraceHistoryMapper.selectAll();
    }

    @Override
    public PageInfo<CustomerTraceHistory> query(CustomerQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<CustomerTraceHistory> list = customerTraceHistoryMapper.queryList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void editHistoryTrace(CustomerTraceHistory entity) {
        Employee emp = new Employee();
        emp.setId(UserContext.getCurrentEmp().getId());
        entity.setInputUser(emp);
        customerTraceHistoryMapper.updateByPrimaryKey(entity);
    }

}
