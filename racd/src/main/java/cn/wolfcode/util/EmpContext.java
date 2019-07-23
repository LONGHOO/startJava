package cn.wolfcode.util;

import cn.wolfcode.crud.domain.Employee;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @Author: 十一
 * @Date: 2019-05-04 15:00
 * @Descrption
 **/
public class EmpContext {

    private EmpContext(){}

    public static final String EMPLOYEE_IN_SESSION =  "EMPLOYEE_IN_SESSION";

    public static final String PERMISSIONS_IN_SESSION = "PERMISSIONS_IN_SESSION";

    /**
     *
     * 功能描述:
     * @param:  获取到当前会话的session
     * @return:
     * @auther: 十一
     * @date:
     */
    public static HttpSession getSession(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes req = (ServletRequestAttributes)requestAttributes;
        return req.getRequest().getSession();
    }

    /**
     *
     * 功能描述:将当前用户的信息设置到Session中
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static void setEmployeeInSession(Employee employee){
        getSession().setAttribute(EMPLOYEE_IN_SESSION,employee);
    }

    /**
     *
     * 功能描述: 将当前用户的所有权限表达式的列表保存到session中
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static void setPermissionsInSession(Set<String> set){
        getSession().setAttribute(PERMISSIONS_IN_SESSION,set);
    }

    /**
     *
     * 功能描述: 获取session中的用户信息
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static Employee getEmployeeInSession(){
        return (Employee)getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    /**
     *
     * 功能描述: 获取用户的所有权限
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static Set<String> getPermissionInSession(){
        return (Set<String>) getSession().getAttribute(PERMISSIONS_IN_SESSION);
    }

}
