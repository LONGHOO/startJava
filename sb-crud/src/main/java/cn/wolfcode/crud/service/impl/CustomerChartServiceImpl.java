package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.mapper.CustomerChartMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ICustomerChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CustomerChartServiceImpl implements ICustomerChartService {

    @Autowired
    private CustomerChartMapper customerChartMapper;

    @Override
    public List<Map<String, Object>> listCustomerCharts(QueryObject qo) {
        return customerChartMapper.selectCustomerChart(qo);
    }

}
