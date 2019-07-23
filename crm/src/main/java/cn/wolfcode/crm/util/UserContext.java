package cn.wolfcode.crm.util;

import cn.wolfcode.crm.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Set;

//用户操作相关的工具类
public class UserContext {

    private UserContext(){}


    public static Employee getCurrentEmp(){
        return (Employee)SecurityUtils.getSubject().getPrincipal();
    }


}
