package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.bussniss.domain.RechargeOffline;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RechargeOfflineMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RechargeOffline record);

    RechargeOffline selectByPrimaryKey(Long id);

    List<RechargeOffline> selectAll();

    int updateByPrimaryKey(RechargeOffline record);

    RechargeOffline getByPrimaryKey(Long id);

    List<RechargeOffline> query(QueryObject qo);
}