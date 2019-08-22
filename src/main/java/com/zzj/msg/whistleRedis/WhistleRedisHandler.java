package com.zzj.msg.whistleRedis;

import com.zzj.security.encrypter.MD5Cheater;
import com.zzj.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Administrator on 2019/1/7.
 */
@Slf4j
public class WhistleRedisHandler  {

    public WhistleRedisHandler() {
    }

    public static class Getter extends JedisPubSub{

        @Override
        public void onMessage(String channel, String message) {
            //订阅消息
            System.gc();
            if(MD5Cheater.md5(Constants.CHANNEL_KEY).equals(message)){
                this.unsubscribe(channel);
                log.info("YesCommander,Cancel This Channel [" + channel +"]");
            }
            log.info("message is comming: channel is: " + channel );
            log.info("message is comming: message is: " + message );
        }

        @Override
        public void onSubscribe(String channel, int subscribedChannels) {
            // 订阅了频道 channel
            log.info("Subscribe success: channel is: " + channel );
            log.info("Subscribe success: subscribedChannels is: " + subscribedChannels );
        }

        @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {
            log.info("Unsubscribe channel: channel is: " + channel );
            log.info("Unsubscribe channel: subscribedChannels is: " + subscribedChannels );
        }

    }
    public static class Sender {
        public static void send(Jedis jedis,String channel, String message){
            jedis.publish(channel,message);
        }
    }


}
