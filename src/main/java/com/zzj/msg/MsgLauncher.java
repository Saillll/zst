package com.zzj.msg;

import com.zzj.msg.whistleRedis.WhistleRedisThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2019/1/7.
 */
@Service
public class MsgLauncher {

    @Autowired
    JedisPool jedisPool;
    @Autowired
    private RedisTemplate redisTemplate;

    public MsgLauncher() {}

    public  void lanuch (String userID) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            WhistleRedisThread thread = new WhistleRedisThread(userID,jedisPool);
            thread.start();
        }
    }
    public  void prepared (String userID) {
        WhistleRedisThread thread = new WhistleRedisThread(userID,jedisPool,true);
        thread.start();
    }

}
