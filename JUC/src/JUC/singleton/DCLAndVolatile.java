package JUC.singleton;

/**
 * @Author: 十一
 * @Date: 2019-07-17 23:14
 * @Descrption
 **/
public class DCLAndVolatile {
    public static void main(String[] args){
//        System.out.println(Student.getInstance()==Student.getInstance());
//        System.out.println(Student.getInstance()==Student.getInstance());
//        System.out.println(Student.getInstance()==Student.getInstance());
//        System.out.println(Student.getInstance()==Student.getInstance());
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Student.getInstance()==Student.getInstance());
            },i+"").start();
        }
    }
}

class Student{

    private Student(){
        System.out.println("实例话student");
    }

    //添加volatile关键字是为了方式编译器将编译后的代码重排序
    private static volatile Student student = null;

    //DCL （Double check lock 双端检锁机制），synchronized(Student.class)前后都做判断
    public static Student getInstance(){
        if(student == null){
            synchronized(Student.class){
                if(student == null){
                    student = new Student();
                }
            }
        }
        return student;
    }
}
