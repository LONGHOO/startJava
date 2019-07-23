package longhoo.thread.demo.threaddemo;

/**
 * @Author: 十一
 * @Date: 2019-03-21 21:18
 * @Descrption
 **/
public class EnergyTransferTask  implements Runnable{

    //能量系统
    private EnergySystem energySystem = null;

    //转移的id
    private int from = 0;

    //最大转移能量数
    private double maxAmout = 0;

    private int delayTime = 10;
    public EnergyTransferTask(EnergySystem system,int from ,double maxAmout){
        this.energySystem = system;
        this.from = from;
        this.maxAmout = maxAmout;
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            energySystem.transfer(from,(int)(energySystem.getAmoutLength() * Math.random()),maxAmout * Math.random());
            try {
                Thread.sleep((int) (delayTime * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
