package JUC.moniter;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 十一
 * @Date: 2019-07-15 22:21
 * @Descrption
 **/
public class ThreadMoniter {
    public static void main(String[] args){
        Print print = new Print();
       // Print print1 = new Print();

        new Thread(()->{
            print.printA();
        }).start();
        new Thread(()->{
            print.printB();
        }).start();
        new Thread(()->{
            print.printC();
        }).start();
    }
}

class Print{

    public static synchronized void printA(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A");
    }
    public static synchronized void printB(){
        System.out.println("B");
    }
    public void printC(){
        System.out.println("C");
    }
}
