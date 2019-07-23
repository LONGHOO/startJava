package com.thread;

/**
 * @Author: 十一
 * @Date: 2019-04-21 19:07
 * @Descrption
 **/
public class PostMan implements Runnable {

    private Integer count = 0;

    @Override
    public void run() {
        sendPackage();
    }

    public void sendPackage(){

            while(Express.TOTAL_COUNT > 0) {
                synchronized (this) {
                    if(Express.TOTAL_COUNT > 0){
                        try {
                            Thread.sleep(10);
                            // System.out.println(Thread.currentThread().getName()+"送了第"+Express.TOTAL_COUNT+"个包裹");
                            Express.TOTAL_COUNT--;
                            this.count++;
                            System.out.println(Thread.currentThread().getName()+"---------"+this.count);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
            //System.out.println(this.count);

       // System.out.println(Thread.currentThread().getName()+"送了"+count+"个包裹");
    }

}
