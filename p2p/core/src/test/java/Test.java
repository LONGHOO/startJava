/**
 * @Author: 十一
 * @Date: 2019-06-20 11:50
 * @Descrption
 **/
public class Test {

    @org.junit.Test
    public void test(){
        String str = "*abc**gfe***xyz*";
        char[] chars = str.toCharArray();
        StringBuffer charSb = new StringBuffer();
        StringBuffer spacialCharSb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if("*".equals(String.valueOf(chars[i]))){
                spacialCharSb.append(chars[i]);
            }else{
                charSb.append(chars[i]);
            }
        }
        System.out.println(spacialCharSb.toString()+charSb.toString());
    }
}
