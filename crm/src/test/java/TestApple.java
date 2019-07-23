import cn.wolfcode.crm.domain.Department;
import org.junit.Test;

/**
 * @Author: 十一
 * @Date: 2019-05-10 21:44
 * @Descrption
 **/
public class TestApple {

    @Test
    public void testApple() throws Exception{
//        Apple apple = new Apple();
////        apple.setId(123L);
////        apple.setEmail("2342@qq.com");
////        apple.setName("apple name");
////        System.out.println(apple.getJson());

        Department department = new Department();
        department.setName("sdfas");
        department.setSn("sdf");
        department.setId(323L);
        System.out.println(department.getJson());

    }
}
