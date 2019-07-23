package cn.wolfcode.p2p.bussniss.service;

import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.bussniss.domain.PlatformBankInfo;
import cn.wolfcode.p2p.bussniss.domain.RechargeOffline;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-19 18:23
 * @Descrption
 **/
public interface IRechargeOfflineService {

    void saveOrUpdate(RechargeOffline rechargeOffline);

    RechargeOffline getByPrimaryKey(Long id);

    PageInfo<RechargeOffline> query(QueryObject qo);

    List<RechargeOffline> selectAll();

    void audit(Long id, Integer state, String remark);
}
