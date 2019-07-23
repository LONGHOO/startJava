package cn.wolfcode.crud.service.impl;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.mapper.PermissonMapper;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IPermissionService;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.P;

/**
 * @Author: 十一
 * @Date: 2019-05-03 13:41
 * @Descrption
 **/

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissonMapper mapper;

    @Autowired
    private ApplicationContext context;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectAll() {
        return mapper.queryAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        Integer rows = mapper.queryForCount(qo);
        List<Permission> permissions = mapper.queryForList(qo);
        return new PageResult<Permission>(qo.getCurrentPage(), qo.getPageSize(), rows, permissions);
    }

    @Override
    public Integer reloadPermissions() {
        List<String> list = mapper.getAllPermissionsOfPerssionName();
        Map<String, Object> map = context.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllers = map.values();
        Permission permission = new Permission();
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getMethods();
            for (Method method : methods) {
                if(method.isAnnotationPresent(RequiredPermission.class)){
                    RequiredPermission requiedPermission = method.getAnnotation(RequiredPermission.class);
                    String[] value = requiedPermission.value();
                    if(list.contains(value[1])){
                        continue;
                    }
                    permission.setName(value[0]);
                    permission.setExpression(value[1]);
                    mapper.insert(permission);
                }
            }
        }
        return null;
    }

    @Override
    public List<Permission> queryPermissionById(Long id) {
        return mapper.queryPermissionById(id);
    }

}
