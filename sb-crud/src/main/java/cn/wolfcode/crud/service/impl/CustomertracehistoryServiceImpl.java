package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Customertracehistory;
import cn.wolfcode.crud.mapper.CustomertracehistoryMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ICustomertracehistoryService;
import cn.wolfcode.crud.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomertracehistoryServiceImpl implements ICustomertracehistoryService {

    @Autowired
    private CustomertracehistoryMapper customertracehistoryMapper;

    @Override
    public void saveOrUpdate(Customertracehistory entity) {
        if (entity.getId() == null){
            //封装客户的录入时间和录入人
            entity.setInputUser(UserContext.getCurrentEmp());
            entity.setInputTime(new Date());
            customertracehistoryMapper.insert(entity);
        }else{
            customertracehistoryMapper.updateByPrimaryKey(entity);
        }
    }


    @Override
    public PageInfo<Customertracehistory> query(QueryObject qo) {
        //需要分页的sql方法前执行这代码
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(),"his.input_time");
        List<Customertracehistory> pageList = customertracehistoryMapper.selectForList(qo);
        return new PageInfo<>(pageList);
    }

}
