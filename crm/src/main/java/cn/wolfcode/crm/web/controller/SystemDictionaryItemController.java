package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.DictionaryItemQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryItemService;
import cn.wolfcode.crm.service.IDictionaryService;
import cn.wolfcode.crm.util.ResultInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemDictionaryItem")
public class SystemDictionaryItemController {

    @Autowired
    private IDictionaryItemService dictionaryItemService;

    @Autowired
    private IDictionaryService dictionaryService;

    @RequiresPermissions(value = {"字典详情列表","SystemDictionaryItem:list"},logical = Logical.OR)
    @RequestMapping("/list")
    public String list(@ModelAttribute DictionaryItemQueryObject qo, Model model) {
        model.addAttribute("pageInfo", dictionaryItemService.query(qo));
        model.addAttribute("dics",dictionaryService.list());
        if(qo.getParentId()!=null){
            model.addAttribute("dictionary",dictionaryService.get(qo.getParentId()));
        }
        return "systemDictionary/item";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(SystemDictionaryItem entity) {
        try{
            dictionaryItemService.saveOrUpdate(entity);
            return ResultInfo.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Long id) {
        try{
            dictionaryItemService.delete(id);
            return ResultInfo.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }
}
