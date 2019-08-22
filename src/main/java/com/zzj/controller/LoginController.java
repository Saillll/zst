package com.zzj.controller;

import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.entity.User;
import com.zzj.msg.MsgLauncher;
import com.zzj.security.jwt.JWTUtil;
import com.zzj.security.shiro.ShiroHashMatcher;
import com.zzj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by Administrator on 2018/11/13.
 */
@RestController
@Slf4j
public class LoginController {


    @Autowired
    private UserService userService;
    @Autowired
    private MsgLauncher msgLauncher;
//    @PostMapping(value = "/login")
//    public String  loginOrigin(    String u,String p){
//        log.info(u.toString());
//        return "1111";
//    }
//    @PostMapping(value = "/login")
//    public String  login(@ModelAttribute  User user){
//        log.info(user.toString());
//        return "1111";
//    }
//    @PostMapping(value = "/login")
//    public String  login(@ModelAttribute  User user){
//        log.info(user.toString());
//        return "1111";
//    }
    @PostMapping(value = "/login")
    public Odin  login(@RequestBody  User user){
        if(ObjectUtils.isEmpty(user) || StringUtils.isEmpty(user.getU()) || StringUtils.isEmpty(user.getP()) ){
            return new Odin(Hela.NoUser);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getU(),user.getP());
        Subject subject = SecurityUtils.getSubject();
        String message = "success";
        try {
            subject.login(token);// 登陆完毕
        }catch(Exception e) {
            log.info("登陆出错！请稍后再试！");
            if(e instanceof UnknownAccountException){
                return new Odin(Hela.NoUser);
            }else if(e instanceof IncorrectCredentialsException){
                return new Odin(Hela.WrongPassword);
            }else if(e instanceof AuthenticationException){
                return new Odin(Hela.AuthLess);
            }
        }
        //可以获取user信息，做别的事情：登陆后跳转等
       User u = (User)subject.getPrincipal();
        Odin odin =  new Odin(Hela.SUCCESS,token);
        String JWTtoken = JWTUtil.getInstance().initToken(u);
        odin = new Odin(Hela.SUCCESS,JWTtoken);
        msgLauncher.prepared(u.getId());
        msgLauncher.lanuch(u.getId());
        //odin = JWTUtil.getInstance().checkToken(JWTtoken);
//        odin = new Odin(Hela.SUCCESS,JWTtoken);
        return odin;
    }

    @PostMapping(value = "/register")
    public Odin register(@RequestBody  User user){
        if(ObjectUtils.isEmpty(user) || StringUtils.isEmpty(user.getU()) || StringUtils.isEmpty(user.getP()) ){
            return new Odin(Hela.NoUser);
        }
        Odin odin = new Odin();
        // 默认的盐是用户的登录名
        user.setId(UUID.randomUUID().toString());
        user.setP(new ShiroHashMatcher().getCredentialHash(user.getP(),user.getU()));
        odin = userService.regist(user);
        return odin;
    }

}
