package longhoo.enums.demo;

/**
 * @Author:十一
 * @Date:2019-03-03 21:37
 * @Descrption
 **/
public enum EnumDemo {
    SPRING("春天"),SUMMER("夏天"),FALL("秋天"),WINNER("冬天");

    private String name;

    private EnumDemo(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
