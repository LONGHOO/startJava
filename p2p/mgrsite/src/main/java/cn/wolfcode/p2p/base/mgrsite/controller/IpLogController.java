package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.query.IpLogQueryObject;
import cn.wolfcode.p2p.base.service.IIpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 十一
 * @Date: 2019-06-15 18:24
 * @Descrption
 **/
@Controller
public class IpLogController {

    @Autowired
    private IIpLogService ipLogService;

    @LoginRequird
    @RequestMapping("iplog")
    public String iplog(@ModelAttribute("qo") IpLogQueryObject qo, Model model){
        model.addAttribute("pageInfo",ipLogService.query(qo));
        return "ipLog/list";
    }

    @LoginRequird
    @RequestMapping("logDetail")
    public String logDetail(@ModelAttribute("qo") IpLogQueryObject qo, Model model){
        model.addAttribute("pageInfo",ipLogService.query(qo));
        return "ipLog/list_detail";
    }

}
