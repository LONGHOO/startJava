package longhoo.lambda;

/**
 * @Author: 十一
 * @Date: 2019-05-10 22:00
 * @Descrption
 **/
@FunctionalInterface
public interface AbastractClass {

    //只允许存在一个 public abstract 方法
    public abstract void test();

    default void funs(){
        System.out.println("hello world 2");
    }
}
