package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.bussniss.domain.PlatformBankInfo;
import cn.wolfcode.p2p.bussniss.mapper.PlatformBankInfoMapper;
import cn.wolfcode.p2p.bussniss.service.IBankInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 十一
 * @Date: 2019-06-19 18:26
 * @Descrption
 **/
@Service
@Transactional
public class BankInfoServiceImpl implements IBankInfoService {

    @Autowired
    private PlatformBankInfoMapper bankInfoMapper;

    @Override
    public void saveOrUpdate(PlatformBankInfo bankInfo) {
        if(bankInfo.getId() == null){
            bankInfoMapper.insert(bankInfo);
        }else{
            bankInfoMapper.updateByPrimaryKey(bankInfo);
        }
    }

    @Override
    public PlatformBankInfo getByPrimaryKey(Long id) {
        return bankInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<PlatformBankInfo> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(bankInfoMapper.query(qo));
    }

    @Override
    public List<PlatformBankInfo> selectAll() {
        return bankInfoMapper.selectAll();
    }
}
