package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.CustomerTraceHistory;
import cn.wolfcode.crm.query.CustomerQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerTraceHistoryService {

    void saveOrUpdate(CustomerTraceHistory entity);

    List<CustomerTraceHistory> list();

    PageInfo<CustomerTraceHistory> query(CustomerQueryObject qo);

    void editHistoryTrace(CustomerTraceHistory entity);
}