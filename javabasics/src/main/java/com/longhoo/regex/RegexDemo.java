package com.longhoo.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 十一
 * @Date: 2019-03-05 13:39
 * @Descrption
 **/
public class RegexDemo {


    /**
     *  正则表达式常用规则
     *  . 表示匹配任意字符
     *  \w 匹配单词字符【a-zA-z_0-9]
     *  \d 匹配数字 【0-9】
     *  \s 空白字符 【\t\n\0Bf\r】
     *  ^  行的开始
     *  $  行的结尾
     *  \b 单词边界  \b[a-zA-Z]{2}\b
     */

    // 匹配叠词（.）\\1+匹配一个或者多个任意字符
    //$1 表示结束
    //^1 表示结尾
    //
    @Test
    public void testRegex(){
        String str = "我我我......我我......爱...爱爱...学...学......学编程";
        String regex1 = "\\.";
        String regex2 = "(.)\\1+";
        String s = str.replaceAll(regex1, "");
        System.out.println("1、" + s);
        String s2 = s.replaceAll(regex2, "$1");
        System.out.println("s2 = " + s2);
        System.out.println("str.matches(regex) = " + str.matches(regex1));

    }

    /**
     * 练习：获取字符串“Hi ! Don't be moved by yourself Fzz”中为两个字母的单词。即Hi、be、by。
     * 　　分析：根据上面的步骤：
     * 　　第一步，我们要对子串进行匹配，即两个字母的单词，字母可以用[a-zA-Z]来表示，范围是两个，所以regex = "[a-zA-Z]{2}"。
     * 但这样不够准确，我们需要的是单词，而不是三个字母，所以要用到“边界匹配器”，即
     * 功能描述:
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    @Test
    public void testRegexObject() {

        String str = "Hi ! Don't be moved by yourself Fzz";
        String regex = "\\b[a-zA-Z]{2}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("matcher.find() = " + matcher.group());
        }
    }

    @Test
    public void testW(){
        String str = "skdfjal0000_)*[][]00sjkdf";
        String regex = "\\w";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        while(matcher.find()){
            System.out.println(matcher.group());
        }

    }
}
