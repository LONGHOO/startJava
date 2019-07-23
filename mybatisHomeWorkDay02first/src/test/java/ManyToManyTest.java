import com.shiyi.mybatis.domain.Son;
import com.shiyi.mybatis.domain.Student;
import com.shiyi.mybatis.domain.Teacher;
import com.shiyi.mybatis.mapper.StudentMapper;
import com.shiyi.mybatis.mapper.TeacherMapper;
import com.shiyi.util.MybatisHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-10 17:14
 * @Descrption
 **/
public class ManyToManyTest {


    @Test
    public void testInsert(){
        SqlSession session = MybatisHelper.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student = new Student(null, "黑寡妇",null);
        studentMapper.insert(student);
        Teacher teacher = new Teacher(null, "小泽老师");
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        mapper.insert(teacher);
        mapper.insertRelation(student.getId(),teacher.getId());
        session.commit();
        session.close();

    }

    @Test
    public void testDeleteRelation(){
        SqlSession session = MybatisHelper.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        studentMapper.deleteById(7L);
        mapper.deleteRelation(7L,7L);
        session.commit();
        session.close();
    }

    @Test
    public void testStudentById() throws Exception{
        SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsReader("mybatis-config.xml"));
        SqlSession session = factory.openSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student studentById = studentMapper.getStudentById(1L);
        System.out.println(studentById);
        session.close();
    }

    @Test
    public void testSingle(){
        SqlSession session = MybatisHelper.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student1 = studentMapper.getStudentById(2L);
        student1.getName();
        session.close();
//        SqlSession session1 = MybatisHelper.getSession();
//        StudentMapper studentMapper1 = session1.getMapper(StudentMapper.class);
//        Student student2 = studentMapper1.getStudentById(2L);
//        studentMapper1.getStudentSingleById(2L);
//        System.out.println(student2.toString());
//        session1.close();

    }
}
