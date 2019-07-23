package cn.wolfcode.crm.realm;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @Author: 十一
 * @Date: 2019-05-07 20:27
 * @Descrption
 **/
@Component("crmRealm")
public class CRMRealm extends AuthorizingRealm {

    @Override
    public String getName(){
        return "CRMRealm";
    }

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        Employee employee = employeeService.queryEmpByUserName(username);
        if(employee == null){
            return null;
        }
        return new SimpleAuthenticationInfo(employee,employee.getPassword(),
                ByteSource.Util.bytes(employee.getName()),
                getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前用户的信息
        Employee employee = (Employee) principals.getPrimaryPrincipal();
        //判断是否是管理员，如果是则添加所有的权限
        if(employee.isAdmin()){
            info.addStringPermissions(Arrays.asList("*:*"));
            info.addRole("admin");
            return info;
        }
        //查询当前用户的角色编码
        List<String> roles = employeeService.querySnByPrimaryKey(employee.getId());
        //查询出当前用户的权限列表
        Set<String> permissions = permissionService.queryPermissionsByEmpId(employee.getId());
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }

    /**
     *
     * 功能描述: 清空缓存
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */

    public void cleanCache(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
