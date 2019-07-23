package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.Storage.QiniuStorage;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: 十一
 * @Date: 2019-05-22 21:25
 * @Descrption
 **/
@RequestMapping("travelCommend")
@Controller
public class TravelCommendController {

    @Autowired
    private ITravelCommendService travelCommendService;

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public Result saveOrUpdate(TravelCommend travelCommend, MultipartFile file){

        if(null != file && file.getSize() > 0){
            try {
                String key = QiniuStorage.uploadImage(file.getBytes());
                String url = QiniuStorage.getUrl(key);
                travelCommend.setCoverUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
                return Result.getResult(e.getMessage(), null);
            }
        }
        travelCommendService.saveOrUpdate(travelCommend);
        return Result.getResult(null,null);
    }

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") TravelCommendQueryObject qo, Model model){
        qo.setOrderBy("schedule desc");
        model.addAttribute("pageInfo",travelCommendService.queryList(qo));
        return "travelCommend/list";
    }

}
