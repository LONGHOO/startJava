package JUC.volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 十一
 * @Date: 2019-07-17 20:26
 * @Descrption
 **/
public class TestVolatile {
    public static void main(String[] args){
        myData myData = new myData();
        new Thread(()->{
            System.out.println("线程开始前"+myData.startNum);
            try {
                TimeUnit.SECONDS.sleep(1);
                myData.addStartNum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程1").start();
        while(myData.startNum == 10){

        }
        System.out.println("myData的值改变" + myData.startNum);
    }
}

class myData{
    volatile int startNum = 10;
    public void addStartNum(){
        startNum = startNum + 1;
    }
}
