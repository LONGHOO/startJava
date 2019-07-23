package longhoo.genericity.demo;

/**
 * @Author:十一
 * @Date:2019-03-03 21:56
 * @Descrption 泛型的测试类
 **/
public class GenericityTest {
        public static void main(String[] args){
            GenericityDemo<String> genericity = new GenericityDemo<String>("hello world");
            String key = genericity.getKey();
            System.out.println(key);
        }
}
