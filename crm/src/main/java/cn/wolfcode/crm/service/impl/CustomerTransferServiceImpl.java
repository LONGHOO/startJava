package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.CustomerTransfer;
import cn.wolfcode.crm.mapper.CustomerMapper;
import cn.wolfcode.crm.mapper.CustomerTransferMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerTransferService;
import cn.wolfcode.crm.util.UserContext;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerTransferServiceImpl implements ICustomerTransferService {

    @Autowired
    private CustomerTransferMapper transferMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void saveOrUpdate(CustomerTransfer entity) {
        if(entity.getNewSeller() == null){
            entity.setNewSeller(UserContext.getCurrentEmp());
        }
        entity.setOperateTime(new Date());
        entity.setOperator(UserContext.getCurrentEmp());
        //更新客户的销售员
        customerMapper.updateUserSeller(entity.getId(),entity.getNewSeller().getId());
        transferMapper.insert(entity);
        customerMapper.updateStatus(Customer.STATUS_PATENTIAL,entity.getCustomer().getId().toString());
    }

    @Override
    public PageInfo<CustomerTransfer> queryList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<CustomerTransfer> list = transferMapper.queryList(qo);
        return new PageInfo<>(list);
    }


}
