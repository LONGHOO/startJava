/**
 * @Author: 十一
 * @Date: 2019-05-19 23:48
 * @Descrption
 **/
public class TestBank {
    public static void main(String[] args){
        double money = 10000;
        System.out.println(count(money,1)+money);
    }

    public static double count(double money,int times){
        if(times == 5){
            return money * 0.003;
        }
        return money * 0.003 + count(money+money*0.003,++times);
    }
}
