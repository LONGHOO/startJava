package com.shiyi.redisstart;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

/**
 * @Author: 十一
 * @Date: 2019-07-05 10:45
 * @Descrption
 **/
public class Redis {

    public static void main(String[] args){
        Jedis jedis = new Jedis("192.168.14.229", 6379);
        jedis.auth("wolfcode");
        jedis.zinterstore("store",new ZParams().aggregate(ZParams.Aggregate.MAX),"seta","zseta");
        System.out.println("done");
        jedis.close();
    }
}
