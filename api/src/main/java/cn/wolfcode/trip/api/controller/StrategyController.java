package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.Result;
import com.github.pagehelper.PageInfo;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-05-25 21:32
 * @Descrption
 **/
@RestController
@RequestMapping("strategys")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private IStrategyCatalogService catalogService;

    @RequestMapping("state/{state}")
    public Result getListByType(@PathVariable Integer state) {
        return Result.getResult(null, strategyService.getListByState(state));
    }

    @RequestMapping(value = "hot/{regionId}",headers = "accept=text/html")
    public void hot(StrategyQueryObject qo, HttpServletResponse response) {
        qo.setState(Strategy.STATE_HOT);
        qo.setCurrentPage(1);
        qo.setPageSize(100);
        List<Strategy> hot = strategyService.getHot(qo);
        qo.setState(null);
        qo.setDisable(true);
        qo.setPageSize(4);
        qo.setPageSize(4);
        List<Strategy> all = strategyService.getHot(qo);
        Map map = new HashMap();
        map.put("hot", hot);
        map.put("all", all);
        // 指定模板文件从何处加载的数据源，这里设置成一个文件目录。 cfg.setDirectoryForTemplateLoading(
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        File templeate = new File(servletContext.getRealPath("templates"));


        // 指定模板如何检索数据模型，这是一个高级的主题了...
        // 但先可以这么来用:
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        // 创建根哈希表
        Map root = new HashMap();
        Template temp = null;
        try {
            cfg.setDirectoryForTemplateLoading(templeate);
            cfg.setDateFormat("utf-8");
            temp = cfg.getTemplate("hot.ftl");
            response.setContentType("text/html;charset=utf-8");
            temp.process(map,response.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "hot/{regionId}",headers = "accept=application/json")
    public PageInfo<Strategy> hot(StrategyQueryObject qo) {
        qo.setDisable(true);
        qo.setPageSize(4);
        List<Strategy> all = strategyService.getHot(qo);
        return new PageInfo<>(all);
    }

    @GetMapping(value = "{id}")
    public Result getStrategyInfo(@PathVariable Long id) {
        Strategy strategy = strategyService.getById(id);
        return Result.getResult(null, strategy);
    }

    @GetMapping(value = "{id}/catalogs")
    public Result getCatalogsStrategyId(@PathVariable Long id) {
        List<StrategyCatalog> strategyCatalogs = catalogService.queryForStrategyId(id);
        return Result.getResult(null, strategyCatalogs);
    }
}
