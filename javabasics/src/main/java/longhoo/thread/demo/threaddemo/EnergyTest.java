package longhoo.thread.demo.threaddemo;

/**
 * @Author: 十一
 * @Date: 2019-03-21 21:27
 * @Descrption
 **/
public class EnergyTest {

    public static void main(String[] args){
        EnergySystem system = new EnergySystem(100, 1000d);
        for (int i = 0; i < 100; i++) {
            EnergyTransferTask task = new EnergyTransferTask(system, i, 1000);
            Thread thread = new Thread(task, "线程" + i);
            thread.start();
        }
    }
}
