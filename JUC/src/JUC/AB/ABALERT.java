package JUC.AB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 十一
 * @Date: 2019-07-15 21:57
 * @Descrption
 **/
public class ABALERT {
    public static void main(String[] args){
        Print print = new Print();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                print.printA();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                print.printB();
            }
        }).start();
    }
}

class Print{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private boolean signal = true;
    public void printA(){
        lock.lock();
        try{
            if(!signal){
                condition1.await();
            }
            System.out.println("A");
            signal = false;
            condition2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try{
            if(signal){
                condition2.await();
            }
            System.out.println("B");
            signal = true;
            condition1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
