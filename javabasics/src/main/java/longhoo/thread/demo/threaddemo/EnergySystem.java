package longhoo.thread.demo.threaddemo;

/**
 * @Author: 十一
 * @Date: 2019-03-21 20:54
 * @Descrption
 **/
public class EnergySystem {

//    定义能量盒子maven
    private final double[] energyBoxs;

    private final Object objLock = new Object();

    //初始化
    public EnergySystem(int boxLength, Double boxSize) {
        energyBoxs = new double[boxLength];
        for (int i = 0; i < boxLength; i++) {
            energyBoxs[i] = boxSize;
        }
    }

    //能量系统转移
    public void transfer(int from, int to, double amout) {
        synchronized (objLock) {
            while (energyBoxs[from] < amout) {
                //如果转移的值小于需要转移到能量，则等待
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            energyBoxs[from] -= amout;
            energyBoxs[to] += amout;
            System.out.print("------从"+from+"转移了"+amout+"到"+to);
            System.out.println("能量总数为："+getCount());
            objLock.notifyAll();

        }
    }

    private double getCount() {
        double sum = 0d;
        for (int i = 0; i < energyBoxs.length; i++) {
            sum += energyBoxs[i];
        }
        return sum;
    }

    public int getAmoutLength(){
        return energyBoxs.length;
    }
}
