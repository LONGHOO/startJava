package JUC.productAndCustomerModel;

/**
 * @Author: 十一
 * @Date: 2019-07-14 23:06
 * @Descrption
 **/
public class ProductCustomer {
    public static void main(String[] args){
        Clerk clerk = new Clerk();
        Producter producter = new Producter(clerk);
        Customers customers = new Customers(clerk);
        new Thread(producter,"生产者").start();
        new Thread(customers,"消费者").start();
        new Thread(producter,"生产者1").start();
        new Thread(customers,"消费者1").start();
    }
}

class Clerk{
    private int product = 0;

    public synchronized void get(){
        while(product >=1){
            System.out.println("产品已满！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       // }else{
        }
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+"生产了"+ ++product);
    }

    public synchronized void sale(){
        while(product <=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("产品售空！");
       // }else{
        }
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+"消费了"+ --product);
    }
}

class Producter implements Runnable{

    private Clerk clerk ;
    public Producter(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            clerk.get();
        }
    }
}

class Customers implements Runnable{

    private Clerk clerk ;
    public Customers(Clerk clerk){
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
