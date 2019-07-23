package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.domain.UserInfo;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.service.IAccountService;
import cn.wolfcode.p2p.base.service.ISystemDictionaryItemService;
import cn.wolfcode.p2p.base.service.IUserInfoService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import cn.wolfcode.p2p.base.util.BitStateUtil;
import cn.wolfcode.p2p.base.util.Constants;
import cn.wolfcode.p2p.base.util.UserContext;
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
public class BasicInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IBidRequsetService bidRequsetService;

    /**
     *功能描述 跳转到个人基本信息界面
     * @author 十一
     * @param model
     * @return java.lang.String
     * @date 2019-06-16 20:22
     */
    @LoginRequird
    @RequestMapping("basicInfo")
    public String borrow(Model model){
        model.addAttribute("userInfo", userInfoService.getCurrent());
        model.addAttribute("educationBackgrounds",systemDictionaryItemService
                .getBySn("educationBackground") );
        model.addAttribute("incomeGrades",systemDictionaryItemService
                .getBySn("incomeGrade") );
        model.addAttribute("marriages",systemDictionaryItemService
                .getBySn("marriage") );
        model.addAttribute("kidCounts",systemDictionaryItemService
                .getBySn("kidCount") );
        model.addAttribute("houseConditions",systemDictionaryItemService
                .getBySn("houseCondition") );
        return "basicinfo";
    }

    @LoginRequird
    @RequestMapping("basicInfo_save")
    public String basicInfo_save(UserInfo userInfo){
        userInfo.setId(UserContext.getCurrentUser().getId());
        userInfo.setBitState(BitStateUtil.HAS_BASICINFO);
        userInfoService.saveOrUpdate(userInfo);
       return "redirect:/basicInfo";
    }


}
