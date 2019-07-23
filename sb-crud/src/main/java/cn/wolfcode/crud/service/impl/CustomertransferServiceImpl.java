package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Customer;
import cn.wolfcode.crud.domain.Customertransfer;
import cn.wolfcode.crud.mapper.CustomerMapper;
import cn.wolfcode.crud.mapper.CustomertransferMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ICustomertransferService;
import cn.wolfcode.crud.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomertransferServiceImpl implements ICustomertransferService {

    @Autowired
    private CustomertransferMapper customertransferMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void save(Customertransfer entity) {
        //0:如果页面中没有提交newseller,当前就是吸纳操作
        if(entity.getNewseller()==null){
            entity.setNewseller(UserContext.getCurrentEmp());
        }

        //1:潜在客户移交,然后历史记录,历史执行保存
        //封装历史的操作人和时间
        entity.setOperator(UserContext.getCurrentEmp());
        entity.setOperateTime(new Date());
        customertransferMapper.insert(entity);

        //2:更新当前用户的市场人员
        //根据用户id修改对应的sellerId
        customerMapper.updateSellerIdByPrimaryKey(entity.getCustomer().getId(),entity.getNewseller().getId());
        //3:将当前客户的状态修改为潜在客户
        customerMapper.updateStatus(entity.getCustomer().getId(), Customer.STATUS_POTENTLAL);
    }

    @Override
    public PageInfo<Customertransfer> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(),"his.operate_time");
        List<Customertransfer> pageList = customertransferMapper.selectForList(qo);
        return new PageInfo<>(pageList);
    }
}
