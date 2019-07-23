package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.mapper.PermissionMapper;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Permission> list() {
        return permissionMapper.selectAll();
    }

    @Override
    public PageInfo<Permission> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Permission> pageList = permissionMapper.selectForList(qo);
        return new PageInfo<>(pageList);
    }

    @Autowired
    private ApplicationContext ctx;//获取ioc容器
    @Override
    public void reload() {
        //查询权限表达式
        List<String> expressions = permissionMapper.selectExpressions();
        //获取所有的controller对象
        Map<String, Object> ctlsMap = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> ctls = ctlsMap.values();
        //遍历controller对象,获取方法
        for (Object ctl : ctls) {
            //判断该对象是否是cglib动态代理对象
            if (AopUtils.isCglibProxy(ctl)){
                //拿到原来controller里的每一个声明方法
                Method[] methods = ctl.getClass().getSuperclass().getDeclaredMethods();
                //遍历取方法上的RequiredPermission自定义注解
                for (Method method : methods) {
                    //annotation是取得的注解权限
                    /*RequiresPermissions annotation = method.getDeclaredAnnotation(RequiresPermissions.class);
                    //如果权限不是空,按索引取权限注解里的内容
                    if (annotation != null){
                        String name = annotation.value()[0];//权限名称
                        String exp = annotation.value()[1];//权限表达式
                        //如果当前权限表达式存在,就跳过执行下一个
                        if (expressions.contains(exp)){
                            continue;
                        }
                        //取得权限进行封装
                        Permission permission = new Permission(name,exp);
                        //用新增的mapper接口方法通过sql,添加进数据库
                        permissionMapper.insert(permission);
                    }*/
                }
            }
        }
        /*//查询权限表达式
        List<String> expressions = permissionMapper.selectExpressions();
        //获取所有的controller对象
        Map<String, Object> ctlsMap = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> ctls = ctlsMap.values();
        //遍历controller对象,获取方法
        for (Object ctl : ctls) {
            Method[] methods = ctl.getClass().getDeclaredMethods();
            //遍历取方法上的RequiredPermission自定义注解
            for (Method method : methods) {
                //annotation是取得的注解权限
                RequiredPermission annotation = method.getDeclaredAnnotation(RequiredPermission.class);
                //如果权限不是空,按索引取权限注解里的内容
                if (annotation != null){
                    String name = annotation.value()[0];
                    String exp = annotation.value()[1];
                    //如果当前权限表达式存在,就跳过执行下一个
                    if (expressions.contains(exp)){
                        continue;
                    }
                    //取得权限进行封装
                    Permission permission = new Permission(name,exp);
                    //用新增的mapper接口方法通过sql,添加进数据库
                    permissionMapper.insert(permission);
                }
            }
        }*/
    }
}
