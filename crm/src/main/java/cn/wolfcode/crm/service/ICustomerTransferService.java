package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.CustomerTransfer;
import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerTransferService {

    void saveOrUpdate(CustomerTransfer entity);


    PageInfo<CustomerTransfer> queryList(QueryObject qo);

}
