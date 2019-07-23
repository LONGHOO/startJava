package cn.wolfcode.crud.service;

import cn.wolfcode.crud.domain.Customer;
import cn.wolfcode.crud.domain.Customertracehistory;
import cn.wolfcode.crud.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface ICustomertracehistoryService {

    void saveOrUpdate(Customertracehistory entity);


    PageInfo<Customertracehistory> query(QueryObject qo);

}
