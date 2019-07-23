package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.bussniss.domain.ExpAcount;
import java.util.List;

public interface ExpAcountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExpAcount record);

    ExpAcount selectByPrimaryKey(Long id);

    List<ExpAcount> selectAll();

    int updateByPrimaryKey(ExpAcount record);
}