package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.service.IAccountService;
import cn.wolfcode.p2p.base.service.IRealAuthService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.UserContext;
import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.query.BidRequestQueryObject;
import cn.wolfcode.p2p.bussniss.service.IBidRequestAuditHistoryService;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import cn.wolfcode.p2p.bussniss.service.IPaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-18 21:43
 * @Descrption
 **/
@Controller
public class BorrowInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IBidRequsetService bidRequsetService;

    @Autowired
    private IRealAuthService realAuthService;

    @Autowired
    private IBidRequestAuditHistoryService historyService;

    @Autowired
    private IPaymentScheduleService scheduleService;

    @RequestMapping("borrowInfo")
    @LoginRequird
    public String borrowInfo(Model model){
        UserInfo info = userInfoService.getCurrent();
        model.addAttribute("account",accountService.getCurrent());
        model.addAttribute("minBidRequestAmount", Constants.BORROW_AMOUNT_MIN);
        model.addAttribute("minBidAmount", Constants.BID_AMOUNT_MIN);

        return "exp_borrow_info";
    }


    @RequestMapping("returnMoneyPage")
    @LoginRequird
    public String borrowInfo(Model model, @ModelAttribute("qo")BidRequestQueryObject qo){

        model.addAttribute("pageInfo",
                scheduleService.selectByBorrowUserId(UserContext.getCurrentUser().getId()));
        return "returnmoney_list";
    }

    @LoginRequird
    @RequestMapping("borrow_info")
    public String borrowInfoDetail(Model model,Long id){

        BidRequest bidRequest = bidRequsetService.getByPrimaryKey(id);
        UserInfo info = userInfoService.get(bidRequest.getCreateUser().getId());
        //借款人的info
        model.addAttribute("userInfo",info);
        model.addAttribute("bidRequest",bidRequest);
        model.addAttribute("audits",historyService.queryListByBidRequestId(bidRequest.getId()));
        model.addAttribute("realAuth",realAuthService.get(info.getRealAuthId()));
        if(UserContext.getCurrentUser().getId().equals(info.getId())){
            model.addAttribute("self",true);
        }else{

            model.addAttribute("self",false);
            model.addAttribute("account",accountService.get(info.getId()));
        }

        return "bidrequest/borrow_info";
    }

    @LoginRequird
    @RequestMapping("returnMoney")
    @ResponseBody
    public AjaxResult returnMoney(@ModelAttribute("qo") QueryObject qo, Long id){
        AjaxResult result = null;
        try{
            bidRequsetService.returnMoney(id, Constants.EXP_ACTIONTYPE_RETURN_MONEY);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
