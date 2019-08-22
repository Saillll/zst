package com.zzj.controller;

import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.entity.Articlecomment;
import com.zzj.entity.User;
import com.zzj.security.aop.TokenCut;
import com.zzj.security.jwt.JWTUtil;
import com.zzj.service.ArticlecommentService;
import com.zzj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/1/9.
 */
@RestController
@Slf4j
@RequestMapping(value = "/articlecomment")
public class ArticlecommentController {

    @Autowired
    private ArticlecommentService articlecommentService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAllComments/{articleID}")
    public Odin getCommentsByArticleID(@PathVariable(value = "articleID" )String articleID){
        Odin odin = new Odin();
        List<Articlecomment> list = articlecommentService.getCommentsByArticleID(articleID);
        odin.setLoki(list);
        return odin;
    }

    @PostMapping(value = "/save")
    @TokenCut(required = true)
    public Odin  login(@RequestBody  Articlecomment articlecomment, HttpServletRequest request){
        String userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
        User user = userService.getById(userID);
        if(ObjectUtils.isEmpty(articlecomment) || StringUtils.isEmpty(articlecomment.getComments()) ){
            return new Odin(Hela.FAIL);
        }
        if(StringUtils.isEmpty(articlecomment.getId())){
            articlecomment.setId(UUID.randomUUID().toString());
        }
//        articlecomment.setOriginuserid(user.getId());
//        articlecomment.setOriginusername(user.getNickname());
        articlecomment.setUser(user);
        articlecomment.setCreatedate(new Date());
        articlecommentService.save(articlecomment);
        //可以获取user信息，做别的事情：登陆后跳转等
        Odin odin =  new Odin(Hela.SUCCESS);
        return odin;
    }

    @GetMapping(value = "/getComment/{id}")
    public Odin getCommentByID(@PathVariable(value = "id" )String id){
        Odin odin = new Odin();
        Articlecomment articlecomment = articlecommentService.getCommentByID(id);
        odin.setLoki(articlecomment);
        return odin;
    }
    @GetMapping(value = "/getComment")
    public Odin getCommentByIDRequestBody(String id){
        Odin odin = new Odin();
        Articlecomment articlecomment = articlecommentService.getCommentByID(id);
        odin.setLoki(articlecomment);
        return odin;
    }

}
