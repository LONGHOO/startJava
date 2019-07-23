import cn.wolfcode.crm.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @Author: 十一
 * @Date: 2019-05-07 19:20
 * @Descrption
 **/
public class StartShiro {

    @Test
    public void testShiro() throws Exception{
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isAuthenticated());
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "zhangsan");
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("账户出错");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }
        System.out.println(subject.isAuthenticated());
    }

    @Test
    public void testSuper() throws Exception{
        Employee employee = new Employee();
        Method[] methods = employee.getClass().getSuperclass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

    }

    @Test
    public void testMD5() throws Exception{
        Md5Hash hashCode = new Md5Hash("1", "admin",10);
        System.out.println(hashCode);
    }
}
