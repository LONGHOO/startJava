package cn.wolfcode.demo.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: 十一
 * @Date: 2019-06-30 22:13
 * @Descrption
 **/
public class MyMain {

    public static void main(String[] args){
        FutureTask<String> task = new FutureTask<>(new MyCallAble());
        Thread thread = new Thread(task);
        thread.start();

        String resultValue = null;
        try {
            resultValue = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(resultValue);
    }
}
