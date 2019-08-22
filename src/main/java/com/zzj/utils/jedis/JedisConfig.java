package com.zzj.utils.jedis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2019/1/7.
 */
@Slf4j
@Configuration
public class JedisConfig   {

    @Value(value = "${spring.redis.host}")
    private String host;
    @Value(value = "${spring.redis.port}")
    private int port;
    @Value(value = "${spring.redis.password}")
    private String password;
    @Value(value = "${spring.redis.timeout}")
    private int timeout;

    @Value(value = "${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value(value = "${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value(value = "${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value(value = "${spring.redis.jedispool.max-wait}")
    private long maxWaitMillis;


    public JedisConfig() {
    }

    @Bean
    public JedisPool JedisFactory(){
        log.info("==============================JedisPool=============================");
        log.info(host);
        log.info("==============================JedisPool=============================");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,password);

        log.info("redis地址：" + host + ":" + port);
        return  jedisPool;
    }

}
