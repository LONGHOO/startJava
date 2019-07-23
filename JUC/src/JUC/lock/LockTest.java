package JUC.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 十一
 * @Date: 2019-07-14 18:24
 * @Descrption
 **/
public class LockTest {
    public static void main(String[] args){
        Locks locks = new Locks();
        new Thread(locks,"线程1").start();
        new Thread(locks,"线程2").start();
        new Thread(locks,"线程3").start();
    }

}

class Locks implements Runnable{

    private int ticks = 100;
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            try {
                lock.lock();
                if(ticks > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"剩余票数为"+--ticks);
                }
            }finally {
                lock.unlock();
            }

        }
    }
}
