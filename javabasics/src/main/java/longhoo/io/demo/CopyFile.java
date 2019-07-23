package longhoo.io.demo;
import java.io.*;
import java.util.Date;

/**
 * @Author:十一
 * @Date:2019-03-03 22:02
 * @Descrption
 **/
public class CopyFile{


        public static void main(String[] args){
            File file = new File("/Users/minger/Desktop/123.txt");
            String fileName = "/Users/minger/Desktop/123.txt";
            InputStreamReader isr = null;


//            Thread.currentThread().getContextClassLoader().getResourceAsStream()
            try {
                String str = "hhello world";
                System.out.println("str = " + str);
                FileInputStream fis = new FileInputStream(file);
                isr = new InputStreamReader(fis,"Utf-8");
                System.out.println((char)isr.read());
                char[] buff = new char[10];
                int b = 0;
                while((b=isr.read(buff,0,buff.length))!= -1){
                    System.out.println(String.valueOf(buff));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        public static  void test(){

        }
}
