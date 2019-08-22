package com.zzj.controller;

import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.entity.User;
import com.zzj.security.aop.TokenCut;
import com.zzj.security.jwt.JWTUtil;
import com.zzj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by Administrator on 2018/11/15.
 */
@RestController
@Slf4j
@RequestMapping(value = "user")
public class UserController {


    @Autowired
    private UserService userService;
    @GetMapping(value = "/getUser")
    public String getUser(){
        String user = "";
        String json= userService.findAll();
        System.out.println(json);

        return json;
    }
    @TokenCut(required = true)
    @GetMapping(value = "/getSelf")
    public Odin getSelf(HttpServletRequest request){
        Odin odin = new Odin(Hela.SUCCESS);
        String id = "";
        String token = "";
        token = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(token)){
            log.info(token);
            id = JWTUtil.getInstance().getUserID(token);
            log.info(token);
        }
        User user= userService.getById(id);
        odin.setLoki(user);
        return odin;
    }
    @TokenCut(required = true)
    @PostMapping(value = "/save")
    public Odin  save(@RequestBody User user){
        if(ObjectUtils.isEmpty(user) || StringUtils.isEmpty(user.getId()) ){
            return new Odin(Hela.FAIL);
        }
        if(StringUtils.isEmpty(user.getId())){
            user.setId(UUID.randomUUID().toString());
        }
        userService.save(user);
        Odin odin =  new Odin(Hela.SUCCESS);
        return odin;
    }
    @GetMapping(value = "/dualCheck/{u}")
    public Odin dualCheck(@PathVariable(value = "u")String u){
        Odin odin = new Odin();
        boolean isUnique = userService.dualCheck(u);
        if(!isUnique){
            odin.setLoki(Hela.DuplicateUser);
        }
        return odin;
    }

}
