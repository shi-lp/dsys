package com;


import redis.clients.jedis.Jedis;

public class JedisClient {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("39.105.66.208",6379);
        jedis.set("usertb1","a1234");
        String j = jedis.get("usertb1");
        System.out.println(j);
        jedis.close();
    }
}
