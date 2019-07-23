package cn.wolfcode.p2p.base.service.impl;

import cn.wolfcode.p2p.base.domain.IpLog;
import cn.wolfcode.p2p.base.mapper.IpLogMapper;
import cn.wolfcode.p2p.base.query.IpLogQueryObject;
import cn.wolfcode.p2p.base.service.IIpLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-15 18:23
 * @Descrption
 **/
@Service
public class IIpLogServiceImpl implements IIpLogService {

    @Autowired
    private IpLogMapper ipLogMapper;

    @Override
    public void save(IpLog iplog) {
        ipLogMapper.insert(iplog);
    }

    @Override
    public PageInfo<IpLog> query(IpLogQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()," loginTime desc ");
        List<IpLog> list = ipLogMapper.query(qo);
        return new PageInfo<>(list);
    }
}
