package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.query.IpLogQueryObject;
import cn.wolfcode.p2p.base.service.IIpLogService;
import cn.wolfcode.p2p.base.util.UserContext;
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

    @RequestMapping("iplog")
    public String iplog(@ModelAttribute("qo") IpLogQueryObject qo, Model model){
        qo.setUsername(UserContext.getCurrentUser().getUsername());
        model.addAttribute("pageInfo",ipLogService.query(qo));
        return "iplog_list";
    }
}
