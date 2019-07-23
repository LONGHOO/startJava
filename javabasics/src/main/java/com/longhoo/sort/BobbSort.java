package com.longhoo.sort;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-03-14 15:09
 * @Descrption
 **/
public class BobbSort {

    /**
     * 每一次执行的时候选出最大的一个数，将其放到最后
     * 功能描述:  冒泡排序
     * @auther: 十一
     */
    @Test
    public void bubbleSort(){
        int temp = 0;
        int[] sort = new int[]{0,2,3,-4,8,2,4,5,8,9};
        for (int i = 0; i < sort.length - 1; i++) {
            for(int j = 0;j<sort.length - i -1;j++){
                if(sort[j] > sort[j+1]){
                    temp = sort[j];
                    sort[j] = sort[j + 1];
                    sort[j + 1] = temp ;
                }
            }
        }
        System.out.println(Arrays.toString(sort));
    }

    @Test
    public void test(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("hello", "hello");
        map.put("hello","hel");
        map.put("hello","hele");
        System.out.println(map.size());
    }
}
