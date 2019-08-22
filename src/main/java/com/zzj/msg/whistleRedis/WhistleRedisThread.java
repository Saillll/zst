package com.zzj.msg.whistleRedis;

import com.zzj.security.encrypter.MD5Cheater;
import com.zzj.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Slf4j
public class WhistleRedisThread extends Thread {

    private String userID;
    private JedisPool jedisPool;
    private boolean clean = false;

    @Override
    public void run() {
        Jedis jedis = jedisPool.getResource();
        try {

            if(clean){
                //取消订阅
                WhistleRedisHandler.Sender.send(jedis,userID,MD5Cheater.md5(Constants.CHANNEL_KEY));
                log.info("=================unsubscribe CHANNEL=============================");
                log.info("=================================================================");
                log.info("=================【" + userID + "】===============================");
                log.info("=================================================================");
                log.info("=================unsubscribe CHANNEL=============================");
                return;
            }
            WhistleRedisHandler.Getter getter =  new WhistleRedisHandler.Getter();
            jedis.subscribe(getter,userID);
            log.info("=================subscribe CHANNEL=============================");
            log.info("=================================================================");
            log.info("=================【" + userID + "】===============================");
            log.info("=================================================================");
            log.info("=================subscribe CHANNEL=============================");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return;
        }
    }

    public WhistleRedisThread(String userID,JedisPool jedisPool) {
        this.userID = userID;
        this.jedisPool = jedisPool;
    }
    public WhistleRedisThread(String userID,JedisPool jedisPool,boolean clean) {
        this.userID = userID;
        this.jedisPool = jedisPool;
        this.clean = clean;
    }

    public WhistleRedisThread() {
    }
}
