package longhoo.lambda;

/**
 * @Author: 十一
 * @Date: 2019-05-10 22:05
 * @Descrption
 **/
public class InstatnceClass implements AbastractClass {

    AbastractClass target;

    public InstatnceClass(AbastractClass abs){
        target = abs;
    }

    @Override
    public void test() {
        if(target != null){
            target.test();
        }
    }
}
