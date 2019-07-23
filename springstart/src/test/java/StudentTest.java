import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shiyi.mp.beans.Student;
import com.shiyi.mp.mapper.StudentMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-04-08 21:51
 * @Descrption
 **/
public class StudentTest {
    private String name;

    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private StudentMapper mapper;

    @Test
    public void testGetStudentList() {
        List<Student> list = mapper.selectList(new Wrapper<Student>() {
            @Override
            public String getSqlSegment() {
                return null;
            }
        });
        for (Student stu : list) {
            System.out.println(stu);
        }
    }

    @Test
    public void testConnection() throws SQLException {
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        System.out.println(dataSource);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testSelectOne() {
        Student stu = new Student();
        stu.setName("赵二");
        stu.setAge(23);
        Student student = mapper.selectOne(stu);
        System.out.println(student);
    }


    @Test
    public void testSelectByPrimaryKey() {
        Student student = mapper.selectById(1);
        System.out.println(student);
    }

    @Test
    public void testSelectByCondition() {
        Student stu = new Student();
        stu.setName("赵二");
        List<Student> list = mapper.selectList(new EntityWrapper<Student>(stu));
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void testBatchByid() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Student> result = mapper.selectBatchIds(list);
        for (Student student : result) {
            System.out.println(student);
        }

    }

    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "赵二");
        map.put("age", 23);
        List<Student> list = mapper.selectByMap(map);
        for (Student student : list) System.out.println(student);
    }

    @Test
    public void testSelectByPage() {
        List<Student> list = mapper.selectPage(new Page<Student>(2, 2), null);
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void testDeleteById() {
        Integer integer = mapper.deleteById(1L);
        mapper.deleteById(1L);
        System.out.println(integer);
    }

    @Test
    public void deleteBatchByIds() {
        List<Integer> list = Arrays.asList(5, 6, 7, 8, 9);
        Integer count = mapper.deleteBatchIds(list);
        System.out.println(count);
    }

    @Test
    public void deleteByCondition() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "赵二");
        map.put("age", 23);

        Integer count = mapper.deleteByMap(map);
        System.out.println(count);
    }

    @Test
    public void testSpringDI(){
        System.out.println(mapper);
        Student student= ctx.getBean("student", Student.class);
        System.out.println(student);
    }
}
