package cn.wolfcode.crud.util;

import cn.wolfcode.crud.domain.Employee;
//import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;
import java.util.Set;

//用户对象的上下文工具类
public class UserContext {
    public static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
    public static final String EXPRESSIONS_IN_SESSION = "EXPRESSIONS_IN_SESSION";

    private UserContext(){}

    private static HttpSession getSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest().getSession();
    }

    //把当前登录成功的用户对象存入session
    public static void setCurrentUser(Employee emp) {
        getSession().setAttribute(EMPLOYEE_IN_SESSION, emp);
    }

    //用于取信息效验他的权限
    public static Employee getCurrentUser() {
        return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    //把当前登录成功的用户权限表达式存入session
    public static void setExpressions(Set<String> exps) {
        getSession().setAttribute(EXPRESSIONS_IN_SESSION, exps);
    }

    public static Set<String> getExpressions() {
        return (Set<String>) getSession().getAttribute(EXPRESSIONS_IN_SESSION);
    }

    public static Employee getCurrentEmp(){
        //return (Employee) SecurityUtils.getSubject().getPrincipal();
        return null;
    }
}
