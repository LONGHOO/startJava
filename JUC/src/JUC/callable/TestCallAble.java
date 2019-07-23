package JUC.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: 十一
 * @Date: 2019-07-14 18:09
 * @Descrption CallAble必须配置Future接口使用，一般使用FutureTask，
 * 可以获取到线程的执行结果，也能实现闭锁的功能,获取是个线程执行的结果
 **/
public class TestCallAble {
    public static void main(String[] args) {
        Call call = new Call();
        Integer sum = 0;
        try {
            for (int i = 0; i < 10; i++) {
                FutureTask<Integer> task = new FutureTask<>(call);
                new Thread(task).start();
                Integer result = task.get();
                sum += result;
            }
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class Call implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}