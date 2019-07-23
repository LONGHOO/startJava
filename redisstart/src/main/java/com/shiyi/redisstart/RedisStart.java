package com.shiyi.redisstart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.ZParams;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: 十一
 * @Date: 2019-07-04 19:40
 * @Descrption
 **/
@Controller
public class RedisStart {

    @Autowired
    private RedisTemplate<String,String>  temp;

    @RequestMapping("temp")
    @ResponseBody
    public String temp() throws ExecutionException, InterruptedException {
        Long before = System.currentTimeMillis();
        InsertDB insertDB = new InsertDB(temp);
        for(int i=0;i<100;i++){
            Thread thread = new Thread(insertDB);
            thread.setName("shiyi"+i);
            thread.start();
        }
        System.out.println(insertDB);
        Integer count  = 0;

        System.out.println("总共用时"+(System.currentTimeMillis() - before));
        return "ok";
    }


}

class InsertDB implements Runnable{
    private RedisTemplate<String,String>  temp;
    InsertDB(RedisTemplate<String,String>  temp){
        this.temp = temp;
    }


    @Override
    public void run(){
        for(int i=0;i<100000;i++){
            String val = Thread.currentThread().getName()+i;
            System.out.println(val);
            temp.opsForValue().set(val,val);
        }
    }
}