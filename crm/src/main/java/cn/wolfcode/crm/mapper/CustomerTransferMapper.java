package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.CustomerTransfer;
import cn.wolfcode.crm.query.QueryObject;
import java.util.List;

public interface CustomerTransferMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerTransfer record);

    CustomerTransfer selectByPrimaryKey(Long id);

    List<CustomerTransfer> selectAll();


    List<CustomerTransfer> queryList(QueryObject qo);

    int updateByPrimaryKey(CustomerTransfer record);


}