package cn.wolfcode.p2p.base.mgrsite.controller;

import cn.wolfcode.p2p.base.annotation.LoginRequird;
import cn.wolfcode.p2p.base.domain.SystemDictionary;
import cn.wolfcode.p2p.base.domain.SystemDictionaryItem;
import cn.wolfcode.p2p.base.exception.DisplayException;
import cn.wolfcode.p2p.base.query.QueryObject;
import cn.wolfcode.p2p.base.query.SystemDictionaryQueryObject;
import cn.wolfcode.p2p.base.service.ISystemDictionaryItemService;
import cn.wolfcode.p2p.base.service.ISystemDictionaryService;
import cn.wolfcode.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 十一
 * @Date: 2019-06-16 18:50
 * @Descrption
 **/
@Controller
public class SystemDictionaryController {

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @LoginRequird
    @RequestMapping("systemDictionary_list")
    public String systemDictionaryList(@ModelAttribute("qo")QueryObject qo, Model model){
        model.addAttribute("pageInfo", systemDictionaryService.query(qo));
        return "systemdic/systemDictionary_list";
    }

    /**
     *功能描述 业务字典保存
     * @author 十一
     * @param systemDictionary
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-16 19:42
     */
    @LoginRequird
    @RequestMapping("systemDictionary_update")
    @ResponseBody
    public AjaxResult systemDictionary_update(SystemDictionary systemDictionary){
        AjaxResult result = null;
        try{
            systemDictionaryService.saveOrUpdate(systemDictionary);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }


    /**
     *功能描述 根据业务字典id删除字典以及字典明细
     * @author 十一
     * @param id
     * @return cn.wolfcode.p2p.base.util.AjaxResult
     * @date 2019-06-16 19:41
     */
    @LoginRequird
    @RequestMapping("systemDictionary_del")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result = null;
        try{
            systemDictionaryService.deleteById(id);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    /**
     *功能描述 字典详情
     * @author 十一
     * @param
     * @return
     * @date 2019-06-16 19:42
     */
    @LoginRequird
    @RequestMapping("systemDictionaryItem_list")
    public String systemDictionaryItemList(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model){

        model.addAttribute("systemDictionaryGroups", systemDictionaryService.selectAll());
        model.addAttribute("pageInfo", systemDictionaryItemService.query(qo));
        return "systemdic/systemDictionaryItem_list";
    }

    @LoginRequird
    @RequestMapping("systemDictionaryItem_update")
    @ResponseBody
    public AjaxResult systemDictionaryItemUpdate(SystemDictionaryItem item){
        AjaxResult result = null;
        try{
            systemDictionaryItemService.saveOrUpdate(item);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }

    @LoginRequird
    @RequestMapping("systemDictionaryItem_del")
    @ResponseBody
    public AjaxResult systemDictionaryItemDelete(Long id){
        AjaxResult result = null;
        try{
            systemDictionaryItemService.deleteById(id);
            result = new AjaxResult();
        }catch (DisplayException e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
