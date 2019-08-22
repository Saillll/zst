package com.zzj.controller;

import com.google.common.collect.Lists;
import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.entity.Msg;
import com.zzj.entity.User;
import com.zzj.msg.whistleRedis.WhistleRedisHandler;
import com.zzj.security.aop.TokenCut;
import com.zzj.security.jwt.JWTUtil;
import com.zzj.service.MsgService;
import com.zzj.service.UserService;
import com.zzj.utils.jedis.JedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/1/7.
 */
@RestController
@Slf4j
@RequestMapping(value = "/msg")
public class MsgController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    JedisPool jedisPool;

    @Autowired
    private MsgService msgService;

    @Autowired
    private UserService userService;

    @TokenCut
    @RequestMapping(value = "/test")
    public Odin sender(HttpServletRequest request){
        String userID = "";
        userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
//        redisTemplate.convertAndSend(userID,"你好我是MCJIBA" + ",MSG IS "+ userID);
        Jedis jedis = jedisPool.getResource();
        WhistleRedisHandler.Sender.send(jedis,userID,"你好我是MCJIBA" + ",MSG IS "+ userID);
        return  new Odin(Hela.SUCCESS);
    }

    @RequestMapping(value = "/getMsgByUserID")
    @TokenCut(required = true)
    public Odin getAllMsgByUserID(String rua,HttpServletRequest request){
        String userID = "";
        userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
        Odin odin = new Odin();
        List<Msg> msgList = Lists.newArrayList();
        rua = StringUtils.isEmpty(rua) ? "0" : rua;
        msgList = msgService.findAll(userID,rua);
        odin.setLoki(msgList);
        return odin;
    }
    @RequestMapping(value = "/readit/{msgid}")
    @TokenCut(required = true)
    public Odin readit(@PathVariable(value = "msgid")String msgid){
        Odin odin = new Odin();
        msgService.readit(msgid);
        return odin;
    }
    @PostMapping(value = "/saveit")
    @TokenCut(required = true)
    public Odin  informit(@RequestBody Msg msg, HttpServletRequest request){
        String userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
        User user = userService.getById(userID);
        msg.setId(UUID.randomUUID().toString());
        msg.setSource(userID);
        msg.setType("0");
        msg.setInvolvedtype("0");
        msg.setCreatedate(new Date());
        msg.setRead(0);
        msgService.saveit(msg);
        Odin odin =  new Odin(Hela.SUCCESS);
        return odin;
    }

}
