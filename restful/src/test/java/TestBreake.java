/**
 * @Author: 十一
 * @Date: 2019-05-18 16:10
 * @Descrption
 **/
public class TestBreake {


   public static void main(String[] args){
       for (int i = 0; i < 10; i++) {
           for (int i1 = 0; i1 < 10; i1++) {
               System.out.println(i1);
               if(i1 == 5){
                   break;
               }
           }
           System.out.println("outer----------");
       }
   }
}
