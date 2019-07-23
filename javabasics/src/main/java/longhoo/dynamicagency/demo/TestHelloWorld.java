package longhoo.dynamicagency.demo;

import java.lang.reflect.Proxy;

/**
 * @Author:十一
 * @Date:2019-03-03 21:27
 * @Descrption
 **/
public class TestHelloWorld {

    public static void main(String[] args){
        IHelloWorld helloWorld = new HelloWorldImpl();
        HelloWorldHandle helloWorldHandle = new HelloWorldHandle(helloWorld);
        IHelloWorld  proxy = (IHelloWorld)Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                helloWorld.getClass().getInterfaces(),
                helloWorldHandle);
        proxy.sayHello();
    }
}
