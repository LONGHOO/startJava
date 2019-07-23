package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.realm.CRMRealm;
import cn.wolfcode.crm.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private CRMRealm crmRealm;

    //1.获取到Spring容器
    //通过DI注解将容器对象注入到当前Service中
    @Autowired
    private ApplicationContext ctx;

    @Override
    public PageInfo<Permission> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Permission> list = permissionMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void reload() {
        //解决重复数据的问题
        //方式1.将表中的数据全部删除,重新插入
        //方式2.在保存之前,先判断当前权限在表中是否存在,存在就不再保存
        //将表中所有的表达式查询出来
        List<String> expressions = permissionMapper.selectAllExpressions();
        //获取到所有Controller中方法上贴的RequiredPermission注解
        //将注解中传递的参数作为权限数据保存到数据库中
        //2.获取到容器中所有的Controller对象
        Map<String, Object> beans = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllers = beans.values();
        //3.获取到Controller中所有的方法对象
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getSuperclass().getDeclaredMethods();
            //4.获取到方法对象上的RequiredPermission注解
            for (Method method : methods) {
                if(method.isAnnotationPresent(RequiresPermissions.class)){
                    RequiresPermissions requiredPermission = method.getAnnotation(RequiresPermissions.class);
                    //5.获取注解中传递的参数
                    String[] values = requiredPermission.value();
                    //6.将数据保存到数据库中
                    String name = values[0];
                    String expression = values[1];
                    //如果存在,执行下一个方法中的权限操作
                    if(expressions.contains(expression)){
                        continue;
                    }
                    Permission p = new Permission(name,expression);
                    permissionMapper.insert(p);
                }
            }
        }
    }

    @Override
    public Set<String> queryPermissionsByEmpId(Long id) {
        return permissionMapper.selectExpressionByEmpId(id);
    }


    @Override
    public void delete(Long id) {
        if (id != null) {
           // permissionMapper.deleteByPrimaryKey(id);
        }
        //清除缓存
        crmRealm.cleanCache();
    }
    
    @Override
    public List<Permission> list() {
        return permissionMapper.selectAll();
    }
}
