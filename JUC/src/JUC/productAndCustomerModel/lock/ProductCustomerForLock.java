package JUC.productAndCustomerModel.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 十一
 * @Date: 2019-07-14 23:06
 * @Descrption
 **/
public class ProductCustomerForLock {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producter producter = new Producter(clerk);
        Customers customers = new Customers(clerk);
        new Thread(producter, "生产者").start();
        new Thread(customers, "消费者").start();
        new Thread(producter, "生产者1").start();
        new Thread(customers, "消费者1").start();
    }
}

class Clerk {
    private int product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void get() {
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("产品已满！");
                condition.await();
            }
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "生产了" + ++product);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                condition.await();
                System.out.println("产品售空！");
            }
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "消费了" + --product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

class Producter implements Runnable {

    private Clerk clerk;

    public Producter(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            clerk.get();
        }
    }
}

class Customers implements Runnable {

    private Clerk clerk;

    public Customers(Clerk clerk) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            clerk.sale();
        }
    }
}
