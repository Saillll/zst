package com.zzj.controller;

import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.entity.Article;
import com.zzj.security.aop.TokenCut;
import com.zzj.security.jwt.JWTUtil;
import com.zzj.service.ArticleService;
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
 * Created by Administrator on 2018/11/15.
 */
@RestController
@Slf4j
@RequestMapping(value = "/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;
    @GetMapping(value = "/all/{index}")
    @TokenCut(required = false)
    public Odin getArticles(@PathVariable(value = "index" )int index){
        String articles = "";
        List<Article> list= articleService.findAllList(index);
        Odin odin = new Odin(Hela.SUCCESS,list.toArray());
        log.info(odin.toString());

        return odin;
    }
    @GetMapping(value = "/allLimit/{index}")
    @TokenCut(required = true)
    public Odin allLimit(@PathVariable(value = "index" )int index,HttpServletRequest request){
        String articles = "";
        String userID = "";
        userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
        List<Article> list= articleService.findAllListLimit(index,userID);
        Odin odin = new Odin(Hela.SUCCESS,list.toArray());
        log.info(odin.toString());
        return odin;
    }

    @GetMapping(value = "/get/{id}")
//    @TokenCut(required = true)
    public Odin getArticle(@PathVariable(value = "id" )String id){
        Article article= articleService.getOne(id);
        Odin odin = new Odin(Hela.SUCCESS,article);
        log.info(odin.toString());
        return odin;
    }
    @DeleteMapping(value = "/delete/{id}")
    @TokenCut(required = true)
    public Odin deleteArticle(@PathVariable(value = "id" )String id){
        boolean done = articleService.deleteOne(id);
        if(done){
            Odin odin = new Odin(Hela.SUCCESS);
            log.info(odin.toString());
            return odin;
        }else{
            Odin odin = new Odin(Hela.FAIL);
            log.info(odin.toString());
            return odin;
        }
    }
    @GetMapping(value = "/form/{id}")
    @TokenCut(required = true)
    public Odin formArticle(@PathVariable(value = "id" )String id){
        if(StringUtils.isEmpty(id)){
            Odin odin = new Odin(Hela.SUCCESS);
            log.info(odin.toString());
            return odin;
        }
        Article article= articleService.getOne(id);
        Odin odin = new Odin(Hela.SUCCESS,article);
        log.info(odin.toString());
        return odin;
    }

    @PostMapping(value = "/save")
    @TokenCut(required = true)
    public Odin  login(@RequestBody Article article, HttpServletRequest request){
        String userID = "";
        userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
        if(ObjectUtils.isEmpty(article) || StringUtils.isEmpty(article.getTitle()) ){
            return new Odin(Hela.FAIL);
        }
        if(StringUtils.isEmpty(article.getId())){
            article.setId(UUID.randomUUID().toString());
        }
//        article.setCreatedate(new Date());
        article.setWriter(userID);
        articleService.save(article);
        //可以获取user信息，做别的事情：登陆后跳转等
        Odin odin =  new Odin(Hela.SUCCESS);
        return odin;
    }
    @GetMapping(value = "/readOne/{id}")
    public Odin readOne(@PathVariable(value = "id" )String id){
        articleService.readOne(id);
        Odin odin = new Odin();
        return odin;
    }

}
