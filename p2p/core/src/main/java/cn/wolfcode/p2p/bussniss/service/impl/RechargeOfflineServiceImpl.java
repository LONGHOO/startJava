package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.base.domain.Account;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.service.IAccountService;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.UserContext;
import cn.wolfcode.p2p.bussniss.domain.AccountFlow;
import cn.wolfcode.p2p.bussniss.domain.RechargeOffline;
import cn.wolfcode.p2p.bussniss.mapper.RechargeOfflineMapper;
import cn.wolfcode.p2p.bussniss.service.IAccountFlowService;
import cn.wolfcode.p2p.bussniss.service.IRechargeOfflineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-06-19 18:26
 * @Descrption
 **/
@Service
@Transactional
public class RechargeOfflineServiceImpl implements IRechargeOfflineService {

    @Autowired
    private RechargeOfflineMapper rechargeOfflineMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountFlowService accountFlowService;
    @Override
    public void saveOrUpdate(RechargeOffline rechargeOffline) {
        if(rechargeOffline.getId() == null){
            rechargeOffline.setApplier(UserContext.getCurrentUser());
            rechargeOffline.setApplyTime(new Date());
            rechargeOffline.setState(RechargeOffline.STATE_NORMAL);
            rechargeOfflineMapper.insert(rechargeOffline);
        }else{
            rechargeOfflineMapper.updateByPrimaryKey(rechargeOffline);
        }
    }

    @Override
    public RechargeOffline getByPrimaryKey(Long id) {
        return rechargeOfflineMapper.getByPrimaryKey(id);
    }

    @Override
    public PageInfo<RechargeOffline> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(rechargeOfflineMapper.query(qo));
    }

    @Override
    public List<RechargeOffline> selectAll() {
        return rechargeOfflineMapper.selectAll();
    }

    @Override
    public void audit(Long id, Integer state, String remark) {
        //1. 审核记录对象存在
        RechargeOffline recharge = rechargeOfflineMapper.getByPrimaryKey(id);
        //2. 审核记录状态为待审核
        if(recharge == null || recharge.getState() != RechargeOffline.STATE_NORMAL){
            throw new DisplayException("当前充值记录存在异常");
        }
        //审核人
        recharge.setAuditor(UserContext.getCurrentUser());
        //审核时间
        recharge.setAuditTime(new Date());
        // 审核意见
        recharge.setRemark(remark);
        //审核状态
        recharge.setState(state);
        //如果审核通过
        if(state == RechargeOffline.STATE_PASS){
            Account account = accountService.get(recharge.getApplier().getId());
            //对应的申请人账户可用余额增加
            account.setUsableAmount(account.getUnReturnAmount().add(recharge.getAmount()));
            accountService.saveOrUpdate(account);
            //记录流水
            AccountFlow accountFlow = new AccountFlow();
            //记录流水时间
            accountFlow.setActionTime(new Date());
            //记录note
            accountFlow.setNote("用户充值"+recharge.getAmount()+"元");
            //记录流水类型
            accountFlow.setActionType(Constants.ACCOUNT_ACTIONTYPE_RECHARGE_OFFLINE);
            //记录可用流水金额
            accountFlow.setAmount(recharge.getAmount());
            //记录冻结的金额
            accountFlow.setFreezedAmount(Constants.ZERO);
            //记录账户id
            accountFlow.setAccountId(account.getId());
            //记录当前用户可用金额
            accountFlow.setUsableAmount(account.getUsableAmount());
            accountFlowService.insert(accountFlow);
        }
        rechargeOfflineMapper.updateByPrimaryKey(recharge);
    }
}
