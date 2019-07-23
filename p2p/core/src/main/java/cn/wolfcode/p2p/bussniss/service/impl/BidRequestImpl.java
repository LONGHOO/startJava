package cn.wolfcode.p2p.bussniss.service.impl;

import cn.wolfcode.p2p.base.domain.Account;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.service.IAccountService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.UserContext;
import cn.wolfcode.p2p.bussniss.domain.*;
import cn.wolfcode.p2p.bussniss.mapper.BidRequestMapper;
import cn.wolfcode.p2p.bussniss.query.BidRequestQueryObject;
import cn.wolfcode.p2p.bussniss.service.*;
import cn.wolfcode.p2p.bussniss.util.CalculatetUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.lang3.time.DateUtils;
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
public class BidRequestImpl implements IBidRequsetService {

    @Autowired
    private BidRequestMapper bidRequestMapper;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountFlowService accountFlowService;

    @Autowired
    private IBidService bidService;

    @Autowired
    private ISystemAccountService systemAccountService;

    @Autowired
    private IBidRequestAuditHistoryService historyService;

    @Autowired
    private ISystemAccountFlowService systemAccountFlowService;

    @Autowired
    private IPaymentScheduleService paymentScheduleService;

    @Autowired
    private IPaymentScheduleDetailService detailService;

    @Autowired
    private IExpAccountService expAccountService;

    @Autowired
    private IExpAccountFlowService expAccountFlowService;

    @Override
    public void saveOrUpdate(BidRequest bidRequest) {
        if (bidRequest.getId() != null) {
            bidRequestMapper.updateByPrimaryKey(bidRequest);
        } else {
            bidRequestMapper.insert(bidRequest);
        }
    }

    @Override
    public BidRequest getByPrimaryKey(Long id) {
        return bidRequestMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<BidRequest> query(BidRequestQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(bidRequestMapper.query(qo));
    }

    /**
     * 功能描述 保存借款申请
     *
     * @param bidRequest
     * @param bidrequestTypePublish
     * @return void
     * @author 十一
     * @date 2019-06-19 18:30
     */
    @Override
    public void borrowApply(BidRequest bidRequest, int bidrequestTypePublish) {
        /**
         *1. 判断是否满足借款条件，各种认证已完成
         * 2. 判断用户是否只有一个申请
         * 3. 借款金额大于最小借款金额并且小于等于剩余的信用额度
         * 4. 利息大于系统最小的利息，大于系统最大的利息
         * 5. 投资金额大于等于系统最小投资金额，小于总金额的50%
         * 6. 给用户信息UserInfo的state保存当前是否存在借款申请的状态
         * 7. bigDecimal使用CompareTo比较两个值
         **/
        UserInfo info = userInfoService.getCurrent();
        Account account = accountService.getCurrent();
        if(bidrequestTypePublish == Constants.BIDREQUEST_TYPE_NORMAL){
            //判断是否满足借款条件，各种认证已完成
            if (!info.isCanBorrow()) {
                throw new DisplayException("请完善用户身份");
            }
            //判断用户是否有一个申请
            if (info.isHasBidRequest()) {
                throw new DisplayException("不能重复申请");
            }
            //判断借款金额是否超过剩余可借金额
            if(!(bidRequest.getBidRequestAmount().compareTo(account.getRemainBorrowLimit()) <= 0)){
                throw new DisplayException("借款额度大于剩余授信额度");
            }
            //满足要求，可以申请借款
            info.addState(BitStateUtil.HAS_BIDREQUEST_PROCESS);
            info.addState(BitStateUtil.HAS_BIDREQUEST_PROCESS);
            userInfoService.saveOrUpdate(info);
        }
        //借款金额大于最小借款金额并且小于等于剩余的信用额度
        if (!(bidRequest.getBidRequestAmount().compareTo(Constants.BORROW_AMOUNT_MIN) >= 0)) {
            throw new DisplayException("请选择正确的借款金额");
        }
        if (bidRequest.getCurrentRate().compareTo(Constants.BORROW_RATE_MIN) < 0) {
            throw new DisplayException("利率不能低于" + Constants.BORROW_RATE_MIN);
        }

        BidRequest bidReq = new BidRequest();
        bidReq.setApplyTime(new Date());
        bidReq.setTotalRewardAmount(CalculatetUtil.calTotalInterest(bidRequest.getReturnType(),
                bidRequest.getBidRequestAmount(), bidRequest.getCurrentRate(),
                bidRequest.getMonthes2Return()));
        bidReq.setReturnType(bidRequest.getReturnType());
        //设置标类型
        bidReq.setBidRequestType(bidrequestTypePublish);
        bidReq.setBidRequestState(Constants.BIDREQUEST_STATE_APPLY);
        bidReq.setBidRequestAmount(bidRequest.getBidRequestAmount());
        bidReq.setCurrentRate(bidRequest.getCurrentRate());
        bidReq.setMinBidAmount(bidRequest.getMinBidAmount());
        bidReq.setTitle(bidRequest.getTitle());
        bidReq.setDescription(bidRequest.getDescription());
        bidReq.setCreateUser(UserContext.getCurrentUser());
        bidReq.setDisableDays(bidRequest.getDisableDays());
        bidReq.setMonthes2Return(bidRequest.getMonthes2Return());
        bidReq.setDisableDate(DateUtils.addDays(new Date(),bidRequest.getDisableDays()));
        bidRequestMapper.insert(bidReq);
    }

    @Override
    public List<BidRequest> getListFromOneHourLater(Date hour) {
        return bidRequestMapper.getListFromOneHourLater(hour);
    }


    /**
     * 功能描述 投标
     *
     * @param bidRequestId, amount
     * @return void
     * @author 十一
     * @date 2019-06-21 20:40
     */
    @Override
    public void borrowBid(Long bidRequestId, BigDecimal amount) {
        BidRequest bidRequest = bidRequestMapper.selectByPrimaryKey(bidRequestId);
        Account account = accountService.get(UserContext.getCurrentUser().getId());
        ExpAcount expAccount = expAccountService.getCureent();
        //标存在并且标的状态是招标中
        if (bidRequest == null || bidRequest.getBidRequestState() != Constants.BIDREQUEST_STATE_BIDDING) {
            throw new DisplayException("标状态异常");
        }
        BigDecimal remainAmount = bidRequest.getRemainAmount();
        if (amount.compareTo(bidRequest.getMinBidAmount()) >= 0 && amount.compareTo(bidRequest.getRemainAmount()) <= 0) {
            //如果是体验标
            if(bidRequest.getBidRequestType() == Constants.BIDREQUEST_TYPE_PUBLISH
                && amount.compareTo(expAccount.getUsableAmount())<=0){
                expAccount.setUsableAmount(expAccount.getUsableAmount().subtract(amount));
                expAccount.setFreezedAmount(expAccount.getFreezedAmount().add(amount));
                expAccountService.update(expAccount);
                //记录流水
                expAccountFlowService.addExpAccountFlow(amount,Constants.EXP_TYPE_FREEZED,
                        "投标冻结"+amount+"元",expAccount);

                //如果是普通信用标
            }else if(amount.compareTo(account.getUsableAmount()) <= 0
                            && !bidRequest.getCreateUser().getId().equals(UserContext.getCurrentUser().getId())){
                //设置冻结金额
                account.setFreezedAmount(account.getFreezedAmount().add(amount));
                //减少可用金额
                account.setUsableAmount(account.getUsableAmount().subtract(amount));
                accountService.saveOrUpdate(account);
                //保存流水
                accountFlowService.saveAccountFlow(amount, account, "投标了"+amount+"元", Constants.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);
            }else{
                throw new DisplayException("投标异常");
            }
            //设置当前标的已投的总金额
            bidRequest.setCurrentSum(bidRequest.getCurrentSum().add(amount));
            //设置投标的总人数
            bidRequest.setBidCount(bidRequest.getBidCount() + 1);
            //保存投标信息
            Bid bid = new Bid();
            bid.setActualRate(bidRequest.getCurrentRate());
            //设置投标金额
            bid.setAvailableAmount(amount);
            bid.setBidRequestId(bidRequestId);
            bid.setBidRequestState(bidRequest.getBidRequestState());
            bid.setBidRequestTitle(bidRequest.getTitle());
            bid.setBidTime(new Date());
            bid.setBidUser(UserContext.getCurrentUser());
            bidService.saveOrUpdate(bid);
            //判断是否满标(满标）
            if (bidRequest.getBidRequestAmount().compareTo(bidRequest.getCurrentSum()) == 0) {
                if(bidRequest.getBidRequestType() == Constants.BIDREQUEST_TYPE_PUBLISH){
                    bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_APPROVE_PENDING_2);
                }else{
                    bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_APPROVE_PENDING_1);
                }
                bidService.changeStatusByBidRequsetId(bidRequestId, bidRequest.getBidRequestState());
            }
            bidRequestMapper.updateByPrimaryKey(bidRequest);

        }else{
            throw new DisplayException("投标的金额不合法");
        }
    }

    /**
     * 功能描述 满标一审
     *
     * @param \id, state, remark
     * @return void
     * @author 十一
     * @date 2019-06-21 22:05
     */
    @Override
    public void bidrequestAudit1(Long id, int state, String remark) {
        /**
         * 1. 满足审核条件
         *    1. 是否存在
         *    2. 是否是满标一审状态
         * 2. 记录审核信息
         *    1. 设置bidReqest状态为满标二审
         *    2. 投标对象bid也改为满标二审
         *    3. 保存相关信息到BidrequestHistory
         * 3. 审核通过
         * 4. 审核拒绝
         *    1. 标对象状态改为满标审核拒绝
         *    2. 投标bid对象改为满标审核拒绝
         *    3. 退款
         *       1. 投资人账户冻结资金增加
         *       2. 投资人账户可用余额增加
         *       3. 记录流水
         *    4. 借款人正在借款的状态移除
         **/
        BidRequest bidRequest = bidRequestMapper.selectByPrimaryKey(id);
        if (bidRequest == null || bidRequest.getBidRequestState() != Constants.BIDREQUEST_STATE_APPROVE_PENDING_1) {
            throw new DisplayException("当前标状态存在异常");
        }
        historyService.addAuditHistory(state, remark, bidRequest, BidRequestAuditHistory.TYPE_AUDIT1);
        //如果审核通过
        if (state == BidRequestAuditHistory.STATE_PASS) {
            //设置状态为满标2审
            bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_APPROVE_PENDING_2);
            bidService.changeStatusByBidRequsetId(bidRequest.getId(), Constants.BIDREQUEST_STATE_APPROVE_PENDING_2);
        } else {
           //投标失败
            failtBidRequest(bidRequest);
        }
        bidRequestMapper.updateByPrimaryKey(bidRequest);
    }

    private void failtBidRequest(BidRequest bidRequest){
        //审核被拒绝,设置状态为发标拒绝状态
        bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_PUBLISH_REFUSE);
        bidRequest.setCurrentSum(Constants.ZERO);
        bidRequest.setTotalRewardAmount(Constants.ZERO);
        //移除借款人的借款状态
        UserInfo info = userInfoService.get(bidRequest.getCreateUser().getId());
        info.removeState(BitStateUtil.HAS_BIDREQUEST_PROCESS);
        userInfoService.saveOrUpdate(info);
        //返回各个投资人的投资，并记录流水
        List<Bid> bids = bidRequest.getBids();
        Map<Long, Account> map = new HashMap<>();
        Long accountId = null;
        Account tempAccount = null;
        for (Bid bid : bids) {
            accountId = bid.getBidUser().getId();
            //如果account不在map中，那么从数据库中去查询
            if (map.get(accountId) == null) {
                tempAccount = accountService.get(accountId);
                map.put(accountId, tempAccount);
            }
            tempAccount.setUsableAmount(tempAccount.getUsableAmount().add(bid.getAvailableAmount()));
            tempAccount.setFreezedAmount(tempAccount.getFreezedAmount().subtract(bid.getAvailableAmount()));
            //保存流水
            accountFlowService.saveAccountFlow(bid.getAvailableAmount(),
                    tempAccount, "投标失败退款" + bid.getAvailableAmount() + "元", Constants.ACCOUNT_ACTIONTYPE_BID_UNFREEZED);
        }
        for (Account account : map.values()) {
            accountService.saveOrUpdate(account);
        }
    }

    /**
     *功能描述 满标二审
     * @author 十一
     * @param id
     * @param state
     * @param remark
     * @return void
     * @date 2019-06-25 20:49
     */
    @Override
    public void bidrequestAudit2(Long id, int state, String remark) {
        BidRequest bidRequest = this.getByPrimaryKey(id);
        //是否存在并且状态是否为二审
        if (bidRequest == null ||
                bidRequest.getBidRequestState() != Constants.BIDREQUEST_STATE_APPROVE_PENDING_2) {
            throw new DisplayException("当前标状态存在异常");
        }
        //审核信息记录
        historyService.addAuditHistory(state, remark, bidRequest, BidRequestAuditHistory.TYPE_AUDIT2);
        //如果审核通过
        if (state == BidRequestAuditHistory.STATE_PASS) {
            //利息
            BigDecimal principal = null;
            SystemAccount systemAccount = new SystemAccount();
                    //标状态为还款中
            bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_PAYING_BACK);
            //投标对象状态为还款中
            bidService.changeStatusByBidRequsetId(id, Constants.BIDREQUEST_STATE_PAYING_BACK);
            //判断当前标的类型
            if(bidRequest.getBidRequestType() == Constants.BIDREQUEST_TYPE_PUBLISH){

            }else{
                //借款人账户
                Account account = accountService.get(bidRequest.getCreateUser().getId());
                //设置可用金额
                account.setUsableAmount(account.getUsableAmount().add(bidRequest.getBidRequestAmount()));
                //设置待还金额(本金加利息）
                account.setUnReturnAmount(account.getUnReturnAmount()
                        .add(bidRequest.getBidRequestAmount().add(bidRequest.getTotalRewardAmount())));
                //减少授信额度
                account.setRemainBorrowLimit(account.getRemainBorrowLimit().subtract(bidRequest.getBidRequestAmount()));
                //保存借款流水
                accountFlowService.saveAccountFlow(bidRequest.getBidRequestAmount(), account,
                        "借款到账" + bidRequest.getBidRequestAmount() + "元", Constants.ACCOUNT_ACTIONTYPE_BIDREQUEST_SUCCESSFUL);

                //利息
                principal = bidRequest.getBidRequestAmount().multiply(new BigDecimal(0.05))
                        .setScale(Constants.SCALE_STORE,BigDecimal.ROUND_HALF_UP);
                //借款人账户扣除手续费
                account.setUsableAmount(account.getUsableAmount().subtract(principal));
                //保存借款人账户信息
                accountService.saveOrUpdate(account);
                //保存借款扣除手续费流水
                accountFlowService.saveAccountFlow(principal, account,
                        "扣除服务费" + principal + "元", Constants.ACCOUNT_ACTIONTYPE_CHARGE);
                principal.setScale(Constants.SCALE_STORE, BigDecimal.ROUND_HALF_UP);
                //保存利息
                systemAccount.setUsableAmount(systemAccount.getUsableAmount().add(principal));
                systemAccountService.update(systemAccount);
                //记录系统账号流水
                systemAccountFlowService.addFlowDetail(principal,
                        Constants.SYSTEM_ACCOUNT_ACTIONTYPE_MANAGE_CHARGE, "收到利息" + principal + "元", systemAccount);

                //移除userInfo的正在借款状态码
                UserInfo info = userInfoService.get(bidRequest.getCreateUser().getId());
                //移除正在借款状态
                info.removeState(BitStateUtil.HAS_BIDREQUEST_PROCESS);
                userInfoService.saveOrUpdate(info);
            }
            //查询出所有的投标对象
            List<Bid> bids = bidRequest.getBids();
            //信用标投标人账号集合
            Map<Long, Account> map = new HashMap<>();
            //体验标投标人账号集合
            Map<Long,ExpAcount> expMap = new HashMap<>();
            Long accountId = null;
            Account acc = null;
            ExpAcount expAcount = null;
            for (Bid bid : bids) {
                accountId = bid.getBidUser().getId();
                if(bidRequest.getBidRequestType() == Constants.BIDREQUEST_TYPE_PUBLISH){
                    expAcount = expMap.get(accountId);
                    if(expAcount == null){
                        expAcount = expAccountService.get(accountId);
                        expMap.put(accountId,expAcount);
                    }
                    //保存用户账户信息，后面给每个账户增加利息的时候需要用到
                    acc = map.get(accountId);
                    if(acc == null){
                        acc = accountService.get(accountId);
                        map.put(accountId,acc);
                    }
                    //减少冻结金额
                    expAcount.setFreezedAmount(expAcount.getFreezedAmount().subtract(bid.getAvailableAmount()));
                    //记录体验金额流水
                    expAccountFlowService.addExpAccountFlow(bid.getAvailableAmount(),
                            Constants.EXP_TYPE_SBUSTRCT,
                            "标审核通过，冻结金额减少" + bid.getAvailableAmount() + "元", expAcount);
                }else{
                    acc = map.get(accountId);
                    if(acc == null){
                        acc = accountService.get(accountId);
                        map.put(accountId,acc);
                    }
                    //减少冻结金额
                    acc.setFreezedAmount(acc.getFreezedAmount().subtract(bid.getAvailableAmount()));
                }

            }
            //保存体验标账户状态
            for (ExpAcount value : expMap.values()) {
                expAccountService.update(value);
            }
            //创建还款对象
            List<PaymentSchedule> paymentScheduleList =  createPaymentSchedule(bidRequest);
            for (Account a : map.values()) {
                BigDecimal totalInterest = Constants.ZERO;
                BigDecimal totalPrincipal = Constants.ZERO;
                for (PaymentSchedule paymentSchedule : paymentScheduleList) {
                    List<PaymentScheduleDetail> details = paymentSchedule.getDetails();
                    for (PaymentScheduleDetail detail : details) {
                        //获取到所有投资人的还款详情对象x
                        if(detail.getInvestorId().equals(a.getId())){
                            totalInterest = totalInterest.add(detail.getInterest());
                            totalPrincipal = totalPrincipal.add(detail.getPrincipal());
                        }
                    }
                }
                //代收利息
                a.setUnReceiveInterest(a.getUnReceiveInterest().add(totalInterest));
                //代收本金
                a.setUnReceivePrincipal(a.getUnReceivePrincipal().add(totalPrincipal)
                        .setScale(Constants.SCALE_STORE, BigDecimal.ROUND_HALF_UP));
                //记录投资流水
                accountFlowService.saveAccountFlow(totalPrincipal,a,"投标"+totalPrincipal+"元",Constants.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);
                accountService.saveOrUpdate(a);
            }
        }else{
            //投标失败
            failtBidRequest(bidRequest);
        }
        bidRequestMapper.updateByPrimaryKey(bidRequest);

    }

    /**
     *功能描述 还款
     * @author 十一
     * @param id
    * @param accountActiontypeReturnMoney
     * @return void
     * @date 2019-06-25 22:19
     */
    @Override
    public void returnMoney(Long id, int accountActiontypeReturnMoney) {
        PaymentSchedule schedule = paymentScheduleService.get(id);
        if(schedule == null || schedule.getState() != Constants.PAYMENT_STATE_NORMAL){
            throw new DisplayException("当前还款对象存在异常");
        }
        //判断是否是体验金还款
        if(accountActiontypeReturnMoney == Constants.ACCOUNT_ACTIONTYPE_RETURN_MONEY){
            Account account = accountService.getCurrent();
            if(account.getUsableAmount().compareTo(schedule.getTotalAmount())<0){
                throw new DisplayException("余额不足，请先充值");
            }
            //判断还款对像是否是当前用户，防止盗用别人的信息还款
            if(!schedule.getBorrowUser().getId().equals(UserContext.getCurrentUser().getId())){
                throw new DisplayException("还款对象不是当前用户");
            }
            //设置状态为已还
            schedule.setState(Constants.PAYMENT_STATE_DONE);
            schedule.setPayDate(new Date());
            //还款人账户
            Account borrowAccount = accountService.get(schedule.getBorrowUser().getId());
            //可用金额减少
            borrowAccount.setUsableAmount(borrowAccount.getUsableAmount().subtract(schedule.getTotalAmount()));
            //待还金额减少
            borrowAccount.setUnReturnAmount(borrowAccount.getUnReturnAmount().subtract(schedule.getTotalAmount()));
            //授信额度增加
            borrowAccount.setRemainBorrowLimit(borrowAccount.getRemainBorrowLimit().add(schedule.getPrincipal()));
            //更新用户账户信息
            accountService.saveOrUpdate(borrowAccount);
            //记录借款人还款流水
            accountFlowService.saveAccountFlow(schedule.getTotalAmount(),
                    borrowAccount,"借贷还款"+schedule.getTotalAmount()+"元",
                    Constants.ACCOUNT_ACTIONTYPE_RETURN_MONEY);
            //保存当前还款对象
            paymentScheduleService.saveOrUpdate(schedule);
        }

        //获取还款明细
        List<PaymentScheduleDetail> details = schedule.getDetails();
        Map<Long,Account> map = new HashMap<>();
        Long borId = null;
        Account borAcc = null;
        SystemAccount systemAccount = systemAccountService.getSystemAccount();
        for (PaymentScheduleDetail detail : details) {
            //设置还款时间
            detail.setPayDate(new Date());
            borId = detail.getInvestorId();
            borAcc = map.get(borId);
            if (borAcc == null) {
                borAcc = accountService.get(borId);
                map.put(borId, borAcc);
            }
            //更新还款详情
            detailService.saveOrUpdate(detail);
            if(accountActiontypeReturnMoney == Constants.EXP_ACTIONTYPE_RETURN_MONEY){
                borAcc.setUsableAmount(borAcc.getUsableAmount().add(detail.getInterest()));
                borAcc.setUnReceiveInterest(borAcc.getUnReceiveInterest().subtract(detail.getInterest())
                        .setScale(Constants.SCALE_CAL, BigDecimal.ROUND_HALF_UP));
            }else{
                //可用金额增加
                borAcc.setUsableAmount(borAcc.getUsableAmount().add(detail.getTotalAmount()));
                //代收利息减少
                borAcc.setUnReceiveInterest(borAcc.getUnReceiveInterest().subtract(detail.getInterest()));
                //代收本金减少
                borAcc.setUnReceivePrincipal(borAcc.getUnReceivePrincipal().subtract(detail.getPrincipal()));
            }

            //记录流水
            accountFlowService.saveAccountFlow(detail.getTotalAmount(),borAcc,
                    "收取还款"+detail.getTotalAmount(), Constants.ACCOUNT_ACTIONTYPE_CALLBACK_MONEY);
            BigDecimal interest = detail.getInterest()
                    .multiply(new BigDecimal("0.1")).setScale(Constants.SCALE_CAL, BigDecimal.ROUND_HALF_UP);
            systemAccount.setUsableAmount(systemAccount.getUsableAmount().add(interest));
            //保存系统账号流水
            systemAccountFlowService.addFlowDetail(interest,
                    Constants.SYSTEM_ACCOUNT_ACTIONTYPE_INTREST_MANAGE_CHARGE,
                    "收取利息"+interest+"元",systemAccount);
            //借款人账户可用金额减少
            borAcc.setUsableAmount(borAcc.getUsableAmount().subtract(interest));
            //保存利息手续费流水
            accountFlowService.saveAccountFlow(interest,borAcc,
                    "扣除利息手续费"+interest+"元",Constants.ACCOUNT_ACTIONTYPE_INTEREST_SHARE);
        }
        //更新系统账户
        systemAccountService.update(systemAccount);
        //更新借款人账户信息
        for (Account value : map.values()) {
            accountService.saveOrUpdate(value);
        }
        //判断是否已经完成了借款
        if(paymentScheduleService.isReturnMoneyFinished(schedule)){
            BidRequest bidRequest = getByPrimaryKey(schedule.getBidRequestId());
            //更新标对象为已还清
            bidRequest.setBidRequestState(Constants.BIDREQUEST_STATE_COMPLETE_PAY_BACK);
            bidRequestMapper.updateByPrimaryKey(bidRequest);
            List<Bid> bids = bidRequest.getBids();
            for (Bid bid : bids) {
                bid.setBidRequestState(Constants.BIDREQUEST_STATE_COMPLETE_PAY_BACK);
                //更新投标对象状态为已还清
                bidService.saveOrUpdate(bid);
            }
        }
    }

    /**
     *功能描述 创建还款对象
     * @author 十一
     * @param bidRequest
     * @return java.util.List<cn.wolfcode.p2p.bussniss.domain.PaymentSchedule>
     * @date 2019-06-22 19:52
     */
    private List<PaymentSchedule> createPaymentSchedule(BidRequest bidRequest) {
        List<PaymentSchedule> list = new ArrayList<>();
        int months = bidRequest.getMonthes2Return();
        PaymentSchedule schdule = null;
        BigDecimal totalPrincipal = Constants.ZERO;
        BigDecimal totalInterest = Constants.ZERO;
        for (int i = 0; i < months; i++) {
            schdule = new PaymentSchedule();
            schdule.setBidRequestId(bidRequest.getId());
            schdule.setBidRequestTitle(bidRequest.getTitle());
            schdule.setBidRequestType(bidRequest.getBidRequestType());
            schdule.setBorrowUser(bidRequest.getCreateUser());
            //最后还款日期
            schdule.setDeadLine(DateUtils.addMonths(bidRequest.getPublishTime(),1));
            //第几个月
            schdule.setMonthIndex(i+1);
            //状态为，
            schdule.setState(Constants.PAYMENT_STATE_NORMAL);
            schdule.setReturnType(bidRequest.getReturnType());

            //如果不是最后一个月
            if(i < (months - 1)){
                //本期还款本金
                BigDecimal principal = CalculatetUtil.calMonthToReturnMoney(bidRequest.getReturnType(), bidRequest.getBidRequestAmount(), bidRequest.getCurrentRate(), i + 1, bidRequest.getMonthes2Return());
                totalPrincipal = totalPrincipal.add(principal);
                //本期还款本金
                schdule.setPrincipal(principal);
                //本期还款利息
                BigDecimal interest = CalculatetUtil.calMonthlyInterest(bidRequest.getReturnType(), bidRequest.getBidRequestAmount(), bidRequest.getCurrentRate(), i + 1, bidRequest.getMonthes2Return());
                totalInterest = totalInterest.add(interest);
                schdule.setInterest(interest);
                //本期还款总金额
                schdule.setTotalAmount(principal.add(interest));

            }else{
                //本期还款本金
                schdule.setPrincipal(bidRequest.getBidRequestAmount().subtract(totalPrincipal));
                //本期还款利息
                schdule.setInterest(bidRequest.getTotalRewardAmount().subtract(totalInterest));
                //本期还款总金额
                schdule.setTotalAmount(schdule.getPrincipal().add(schdule.getInterest()));
            }
            paymentScheduleService.saveOrUpdate(schdule);
            list.add(schdule);
            //创建每一个还款明细对象
            List<Bid> bids = bidRequest.getBids();
            //前n个还款明细的总利息
            BigDecimal detailTotalInterest = Constants.ZERO;
            //前n个还款本金的总额
            BigDecimal detailTotalPrincipal = Constants.ZERO;
            for (int i1 = 0; i1 < bids.size(); i1++) {
                Bid bid = bids.get(i1);
                PaymentScheduleDetail detail = new PaymentScheduleDetail();
                detail.setPaymentScheduleId(schdule.getId());
                detail.setBidAmount(bids.get(i1).getAvailableAmount());
                detail.setBidId(bid.getId());
                detail.setBidRequestId(bidRequest.getId());
                detail.setBorrowUser(bidRequest.getCreateUser());
                detail.setDeadLine(schdule.getDeadLine());
                detail.setInvestorId(bid.getBidUser().getId());
                if(i1 < bids.size() -1 || bids.size() == 1){
                    //设置本期还款利息
                    BigDecimal detailInterest = (bid.getAvailableAmount().divide(bidRequest.getBidRequestAmount(), Constants.SCALE_CAL, BigDecimal.ROUND_HALF_UP)).multiply(schdule.getInterest())
                            .setScale(Constants.SCALE_STORE,BigDecimal.ROUND_HALF_UP);
                    //保存前n个详情的总利息
                    detailTotalInterest = detailTotalInterest.add(detailInterest).setScale(Constants.SCALE_STORE,BigDecimal.ROUND_HALF_UP);
                    detail.setInterest(detailInterest);
                    BigDecimal detailPrincipal = bid.getAvailableAmount().divide(bidRequest.getBidRequestAmount(), Constants.SCALE_CAL, BigDecimal.ROUND_HALF_UP)
                            .multiply(schdule.getPrincipal().setScale(Constants.SCALE_CAL,BigDecimal.ROUND_HALF_UP))
                            .setScale(Constants.SCALE_STORE,BigDecimal.ROUND_HALF_UP);
                    //保存前n个详情的总本金
                    detailTotalPrincipal = detailTotalPrincipal.add(detailPrincipal).setScale(Constants.SCALE_STORE,BigDecimal.ROUND_HALF_UP);
                    detail.setPrincipal(detailPrincipal);
                    detail.setTotalAmount(detailInterest.add(detailPrincipal));
                }else{
                    detail.setInterest(schdule.getInterest().subtract(detailTotalInterest));
                    detail.setPrincipal(schdule.getPrincipal().subtract(detailTotalPrincipal));
                    detail.setTotalAmount(detail.getInterest().add(detail.getPrincipal()));
                }
                detailService.saveOrUpdate(detail);
                schdule.getDetails().add(detail);
            }
        }
        return list;
    }

}
