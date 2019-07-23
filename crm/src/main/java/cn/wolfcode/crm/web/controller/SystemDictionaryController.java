package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.query.QueryObject;
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
@RequestMapping("/systemDictionary")
public class SystemDictionaryController {

    @Autowired
    private IDictionaryService systemDictionaryService;

    @RequiresPermissions(value = {"字典列表","SystemDictionary:list"},logical = Logical.OR)
    @RequestMapping("/list")
    public String list(@ModelAttribute QueryObject qo, Model model) {
        model.addAttribute("pageInfo", systemDictionaryService.query(qo));
        return "systemDictionary/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(SystemDictionary entity) {
        try{
            systemDictionaryService.saveOrUpdate(entity);
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
            systemDictionaryService.delete(id);
            return ResultInfo.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.fail(e.getMessage());
        }
    }
}
