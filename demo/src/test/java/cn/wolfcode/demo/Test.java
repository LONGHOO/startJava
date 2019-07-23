package cn.wolfcode.demo;

public class Test
{
    private int i=getValue();//第2行
    private int j = 10;
    int getValue(){
        return j;
    }
    public static void main(String[] args) {
        System.out.print(new Test().i);//第9行
 
    }
}