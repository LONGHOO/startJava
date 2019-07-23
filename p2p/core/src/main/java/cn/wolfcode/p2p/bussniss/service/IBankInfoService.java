package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.bussniss.domain.PlatformBankInfo;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-19 18:23
 * @Descrption
 **/
public interface IBankInfoService {

    void saveOrUpdate(PlatformBankInfo bankInfo);

    PlatformBankInfo getByPrimaryKey(Long id);

    PageInfo<PlatformBankInfo> query(QueryObject qo);

    List<PlatformBankInfo> selectAll();
}
