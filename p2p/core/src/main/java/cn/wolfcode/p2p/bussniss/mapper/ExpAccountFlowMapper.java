package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.bussniss.domain.ExpAccountFlow;
import java.util.List;

public interface ExpAccountFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExpAccountFlow record);

    ExpAccountFlow selectByPrimaryKey(Long id);

    List<ExpAccountFlow> selectAll();

    int updateByPrimaryKey(ExpAccountFlow record);
}