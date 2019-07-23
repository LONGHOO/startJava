package com.longhoo.cglib;

import javafx.scene.effect.Reflection;
import net.sf.cglib.proxy.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.TreeSet;

/**
 * @Author: 十一
 * @Date: 2019-03-05 14:33
 * @Descrption
 **/
public class CglibDemo {

    @Test
    public void testCglib(){

    }

    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloWorld.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                Object obj = methodProxy.invokeSuper(o, objects);
                System.out.println("after");
                return obj;
            }
        });
        HelloWorld hw =(HelloWorld) enhancer.create();
        hw.sayHello();
    }


    /**
     *
     * 功能描述:FixedValue用来对所有拦截的方法返回相同的值
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    @Test
    public void testFixedValue(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloWorld.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return null;
            }
        });
        HelloWorld hw =(HelloWorld) enhancer.create();
        System.out.println(hw.sayHello());
        System.out.println(hw.toString());

    }

    /**
     *
     * 功能描述:
     * @param:  使用InvocationHandler容易引器死循环，可以使用MethodInterceptor
     * @return
     * @auther: 十一
     * @date:
     */
    @Test
    public void testInvocationHandler() throws Exception{
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloWorld.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//                if(method.getName().equals("sayHello")){
//                    System.out.println("sayHello` be called");
//                }
                return method.invoke(o,objects);
            }
        });
        HelloWorld hw =(HelloWorld) enhancer.create();
        System.out.println(hw.sayHello());
        System.out.println(hw.toString());
    }

    /**
     *
     * 功能描述: 如果需要对特定的方法进行代理，可以在Enhancer中添加一个CallBackFilter
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    @Test
    public void testCallBackFilter() throws Exception{
        CallbackHelper ch = new CallbackHelper(HelloWorld.class,new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "Hello cglib";
                        }
                    };
                }else{
                    return NoOp.INSTANCE;
                }
            }
        };
        new Thread();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloWorld.class);
        enhancer.setCallbackFilter(ch);
        enhancer.setCallbacks(ch.getCallbacks());
        HelloWorld hw = (HelloWorld) enhancer.create();
        System.out.println(hw.sayHello());
    }
}
