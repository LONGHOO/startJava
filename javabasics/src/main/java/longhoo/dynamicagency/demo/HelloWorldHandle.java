package longhoo.dynamicagency.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author:十一
 * @Date:2019-03-05 12:13
 * @Descrption
 **/
public class HelloWorldHandle implements InvocationHandler {

    private Object obj;
    public HelloWorldHandle(Object target){
        this.obj = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前");
        Object returnResult = method.invoke(obj, args);
        System.out.println("方法执行后");
        return returnResult;
    }
}
