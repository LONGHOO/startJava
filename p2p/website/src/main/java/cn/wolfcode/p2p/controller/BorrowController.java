package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.service.IAccountService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.UserContext;
import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-15 21:55
 * @Descrption
 **/
@Controller
public class BorrowController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IBidRequsetService bidRequsetService;

    @LoginRequird
    @RequestMapping("borrow")
    public String borrow(Model model){
        if(null != UserContext.getCurrentUser()){
            model.addAttribute("userInfo",userInfoService.getCurrent());
            model.addAttribute("account",accountService.getCurrent());
            return "borrow";
        }
        return "redirect:/borrowIndex.html";
    }

    @RequestMapping("borrow_apply")
    @LoginRequird
    @ResponseBody
    public AjaxResult borrowApply(BidRequest bidRequest){
        AjaxResult result = null;
        try{
            bidRequsetService.borrowApply(bidRequest, Constants.BIDREQUEST_TYPE_NORMAL);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;

    }

}
