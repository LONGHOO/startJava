package cn.wolfcode.starter;

/**
 * @Author: 十一
 * @Date: 2019-06-11 15:11
 * @Descrption
 **/
public class HelloService {

    private HelloProperties helloProperties;

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public void sayHello(String name){
        System.out.println(helloProperties.getPrefix()+
                " "+name+" "+helloProperties.getSubfix());
    }
}
