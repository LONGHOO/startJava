package cn.wolfcode.crud.controller;

import cn.wolfcode.crud.domain.Systemdictionary;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.ISystemdictionaryService;
import cn.wolfcode.crud.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemdictionary")
public class SystemdictionaryController {

    @Autowired
    private ISystemdictionaryService systemdictionaryService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute("result",systemdictionaryService.query(qo));
        return "/systemdictionary/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        JSONResult result = new JSONResult();
        try {
            systemdictionaryService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除失败了.");
        }
        return result;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Systemdictionary entity){
        JSONResult result = new JSONResult();
        try {
            systemdictionaryService.saveOrUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("操作失败了.");
        }
        return result;
    }
}
