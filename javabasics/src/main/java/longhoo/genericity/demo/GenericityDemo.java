package longhoo.genericity.demo;

/**
 * @Author:十一
 * @Date:2019-03-03 21:54
 * @Descrption 泛型演示类
 **/
public class GenericityDemo<T> {

    private T key;

    public GenericityDemo(T key){
        this.key = key;
    }

    public T getKey(){
        return this.key;
    }
}
