import com.shiyi.ssm.domain.Department;
import com.shiyi.ssm.mapper.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 十一
 * @Date: 2019-04-20 09:30
 * @Descrption
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {


    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void test(){
        Department one = departmentMapper.queryByMultiParams(1L, "one", "001");
        System.out.println(one.getName());
    }
}
