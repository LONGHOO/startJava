package cn.wolfcode.p2p.bussniss.mapper;

import cn.wolfcode.p2p.bussniss.domain.SystemAccount;
import java.util.List;

public interface SystemAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemAccount record);

    SystemAccount selectByPrimaryKey(Long id);

    List<SystemAccount> selectAll();

    int updateByPrimaryKey(SystemAccount record);

    int existAccount();
}