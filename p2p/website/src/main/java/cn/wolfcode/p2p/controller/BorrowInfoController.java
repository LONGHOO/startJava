package cn.wolfcode.p2p.controller;

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
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import cn.wolfcode.p2p.bussniss.service.IExpAccountService;
import cn.wolfcode.p2p.bussniss.service.IPaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

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
    private IPaymentScheduleService paymentScheduleService;

    @Autowired
    private IExpAccountService expAccountService;

    @RequestMapping("borrowInfo")
    @LoginRequird
    public String borrowInfo(Model model){
        UserInfo info = userInfoService.getCurrent();
        if(!info.isCanBorrow()){
            return "redirect:/borrow";
        }
        if(BitStateUtil.hasState(info.getBitState(),BitStateUtil.HAS_BIDREQUEST_PROCESS)){
            return "borrow_apply_result";
        }
        model.addAttribute("account",accountService.getCurrent());
        model.addAttribute("minBidRequestAmount", Constants.BORROW_AMOUNT_MIN);
        model.addAttribute("minBidAmount", Constants.BID_AMOUNT_MIN);

        return "borrow_apply";
    }

    @LoginRequird
    @RequestMapping("borrow_info")
    public String borrowInfoDetail(Model model,Long id){
        String page = "";
        BidRequest bidRequest = bidRequsetService.getByPrimaryKey(id);
        if(bidRequest.getBidRequestType()==Constants.BIDREQUEST_TYPE_NORMAL){
            UserInfo info = userInfoService.get(bidRequest.getCreateUser().getId());
            model.addAttribute("realAuth",realAuthService.get(info.getRealAuthId()));
            model.addAttribute("userInfo",info);
            if(UserContext.getCurrentUser().getId().equals(info.getId())){
                model.addAttribute("self",true);
            }else{
                model.addAttribute("self",false);
                model.addAttribute("account",accountService.get(UserContext.getCurrentUser().getId()));
            }
            page = "borrow_info";
        }else{
            model.addAttribute("expAccount",expAccountService.getCureent());
            page = "exp_borrow_info";
        }
        //借款人的info
        model.addAttribute("bidRequest",bidRequest);

        return page;
    }

    /**
     *功能描述 投标
     * @author 十一
     * @param bidRequestId, amount
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-21 20:39
     */
    @LoginRequird
    @RequestMapping("borrow_bid")
    @ResponseBody
    public AjaxResult borrowBid(Long bidRequestId, BigDecimal amount){
        AjaxResult result = null;
        try{
            bidRequsetService.borrowBid(bidRequestId,amount);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
    @LoginRequird
    @RequestMapping("borrowBidReturn_list")
    public String borrowBidReturnList(Model model,@ModelAttribute("qo")QueryObject qo){
        model.addAttribute("pageInfo"
                ,paymentScheduleService.selectByBorrowUserId(UserContext.getCurrentUser().getId()));
        model.addAttribute("account", accountService.getCurrent());
        return "returnmoney_list";
    }

    @LoginRequird
    @RequestMapping("returnMoney")
    @ResponseBody
    public AjaxResult returnMoney(@ModelAttribute("qo")QueryObject qo,Long id){
        AjaxResult result = null;
        try{
            bidRequsetService.returnMoney(id,Constants.ACCOUNT_ACTIONTYPE_RETURN_MONEY);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

}
