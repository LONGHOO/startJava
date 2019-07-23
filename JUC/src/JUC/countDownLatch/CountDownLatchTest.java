package JUC.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: 十一
 * @Date: 2019-07-14 17:50
 * @Descrption 闭锁，就是在多线程中等待其他的线程执行完
 **/

public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        CountDown countDown = new CountDown(latch);
        long before = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(countDown).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("总共用时：" + (System.currentTimeMillis() - before));
    }

}


class CountDown implements Runnable {
    private CountDownLatch latch;

    public CountDown(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5000000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                latch.countDown();
            }
        }
    }
}
