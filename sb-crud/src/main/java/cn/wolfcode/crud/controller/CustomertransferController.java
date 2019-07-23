package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.domain.Customertransfer;
import cn.wolfcode.crud.query.PotentialCustomerQueryObject;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ICustomertransferService;
import cn.wolfcode.crud.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customertransfer")
public class CustomertransferController {

    @Autowired
    private ICustomertransferService customertransferService;

    //@RequiresPermissions(value={"跟进历史列表","customertransfer:list"},logical = Logical.OR)
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute("result",customertransferService.query(qo));
        return "/customertransfer/list";
    }

    //@RequiresPermissions(value={"跟进历史新增","customertransfer:save"},logical = Logical.OR)
    @RequestMapping("/save")
    @ResponseBody
    public JSONResult save(Customertransfer entity){
        JSONResult result = new JSONResult();
        try {
            customertransferService.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }
}
