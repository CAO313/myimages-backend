package com.cao.myimages.job;

import com.cao.myimages.constant.RedisConstant;
import com.cao.myimages.utils.FTPUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;


@Component
public class RedisJob {
    @Resource
    private JedisPool jedisPool;
    private FTPUtils ftpUtils = new FTPUtils();
    @Scheduled(cron = "0 0 0 1/1 * ?  ")
    public void Redis(){
        Jedis jedis = jedisPool.getResource();
        Set<String> set = jedis.sdiff(RedisConstant.URL_SERVER,RedisConstant.URL_DB);
        for (String s : set) {
            int idx1 = s.lastIndexOf('/');
            String name = s.substring(idx1+1);
           boolean bo = ftpUtils.deleteFile("/home/www/pages",name);
            System.out.println("deleting.."+s);
            jedis.srem(RedisConstant.URL_SERVER,s);
        }
        jedis.close();
    }
}
