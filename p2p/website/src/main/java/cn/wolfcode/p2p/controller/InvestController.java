package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.bussniss.domain.BidRequest;
import cn.wolfcode.p2p.bussniss.query.BidRequestQueryObject;
import cn.wolfcode.p2p.bussniss.service.IBidRequsetService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 十一
 * @Date: 2019-06-20 10:20
 * @Descrption
 **/
@Controller
public class InvestController {

    @Autowired
    private IBidRequsetService bidRequsetService;

    @RequestMapping("invest")
    @LoginRequird
    public String invest(){
        return "invest";
    }

    /**
     *功能描述 获取标相信信息
     * @author 十一
     * @param qo
     * @return java.lang.String
     * @date 2019-06-20 10:27
     */
    @LoginRequird
    @RequestMapping("invest_list")
    public String investList(Model model, @ModelAttribute("qo") BidRequestQueryObject qo){
        PageInfo<BidRequest> query = bidRequsetService.query(qo);
        model.addAttribute("pageInfo",query);
        return "invest_list";
    }

}
