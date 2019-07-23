package longhoo.lambda;

import com.sun.tools.javac.util.List;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @Author: 十一
 * @Date: 2019-05-10 21:52
 * @Descrption
 **/
public class Start {

    int num  = 10;
    public void addPlusplus(){
        num = 60;
    }
    public static void main(String[] args){
//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               System.out.println("hello world");
//           }
//       }).start();
//
//       new Thread(()->{
//           System.out.println("hello world");
//       }).start();

//        List<String> languages = (List<String>) Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//
        fun(()->{
            System.out.println("hello world");
        });


    }

    public static void fun(AbastractClass clazz){
        clazz.test();
        clazz.funs();
    }

}
