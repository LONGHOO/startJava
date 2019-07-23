package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-05-22 21:25
 * @Descrption
 **/
@RequestMapping("travel")
@Controller
public class TravelController {

    @Autowired
    private ITravelService travelService;

    /**
     *
     * 功能描述: 待审核
     * @auther: 十一
     * @date:   2019-05-22 21:27
     */
    @RequestMapping("audit")
    public String audit(@ModelAttribute("qo") TravelQueryObject qo, Model model){
        if(null == qo.getState()){
            qo.setState(Travel.STATE_AUDIT);
        }
        qo.setOrderBy("t.lastUpdateTime asc");
        model.addAttribute(travelService.query(qo));
        return "travel/list";
    }
      /**
     *
     * 功能描述: 根据id获取游记对应的内容
     * @auther: 十一
     * @date:   2019-05-22 21:27
     */
    @RequestMapping("getContentById")
    @ResponseBody
    public TravelContent getContentById(Long id){
        return travelService.getContentById(id);
    }
   /**
     *
     * 功能描述: 发布/拒绝
     * @auther: 十一
     * @date:   2019-05-22 21:27
     */
    @RequestMapping("changeState")
    @ResponseBody
    public Result changeState(Travel travel){
        travelService.changeState(travel);
        return Result.getResult(null, null);
    }

    /**
     *
     * 功能描述：已发布页面
     * @auther: 十一
     * @date:   2019-05-22 21:27
     */
    @RequestMapping("releaseList")
    public String release(@ModelAttribute("qo") TravelQueryObject qo, Model model){
        qo.setState(Travel.STATE_RELEASE);
        qo.setOrderBy("t.lastUpdateTime desc");
        model.addAttribute(travelService.query(qo));

        return "travel/releaseList";
    }


}
