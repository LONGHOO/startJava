package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.mapper.CustomerMapper;
import cn.wolfcode.crm.mapper.CustomerReportMapper;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.query.ReportQueryObject;
import cn.wolfcode.crm.service.ICustomerReportService;
import cn.wolfcode.crm.service.ICustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerReportServiceImpl implements ICustomerReportService {

    @Autowired
    private CustomerReportMapper mapper;

    @Override
    public List<Map<String, Object>> queryForQueryObject(ReportQueryObject qo) {
        return mapper.queryForQueryObject(qo);
    }

}
