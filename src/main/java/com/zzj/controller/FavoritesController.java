package com.zzj.controller;


import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.entity.Favorites;
import com.zzj.security.aop.TokenCut;
import com.zzj.security.jwt.JWTUtil;
import com.zzj.service.FavoritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;


    @RequestMapping(value ="/favor/{articleid}")
    @TokenCut(required = true)
    public Odin favor(@PathVariable(value = "articleid" )String articleid,HttpServletRequest request){
        String userID = "";
        userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
        Odin odin = new Odin();
        Favorites favorites = new Favorites();
        favorites.setArticleid(articleid);
        favorites.setUserid(userID);
        // 已经收藏的会取消收藏 未收藏的会新增收藏
        favoritesService.favor(favorites);
        return  odin;
    }

    @GetMapping(value = "/allLimit/{index}")
    @TokenCut(required = true)
    public Odin allLimit(@PathVariable(value = "index" )int index,HttpServletRequest request){
        String userID = "";
        userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
        List<Favorites> list= favoritesService.findAllListLimit(index,userID);
        Odin odin = new Odin(Hela.SUCCESS,list.toArray());
        log.info(odin.toString());
        return odin;
    }
    @GetMapping(value = "/getFreeFavorites")
    @TokenCut(required = false)
    public Odin allLimit(int index,String userID){
        List<Favorites> list= favoritesService.findAllListLimit(index,userID);
        Odin odin = new Odin(Hela.SUCCESS,list.toArray());
        log.info(odin.toString());
        return odin;
    }
    @GetMapping(value ="/getLike/{articleid}")
    @TokenCut(required = true)
    public Odin getLike(@PathVariable(value = "articleid" )String articleid, HttpServletRequest request){
        String userID = "";
        userID = JWTUtil.getInstance().getUserID(request.getHeader("Authorization"));
        Odin odin = new Odin();
        Favorites favorites = new Favorites();
        favorites.setArticleid(articleid);
        favorites.setUserid(userID);
        // 已经收藏的会取消收藏 未收藏的会新增收藏
        boolean hasliked = favoritesService.getLike(favorites);
        odin.setLoki(hasliked);
        return  odin;
    }

    @GetMapping(value ="/cancelFavor/{id}")
    @TokenCut(required = true)
    public Odin cancelFavor(@PathVariable(value = "id" )String id, HttpServletRequest request){
        Odin odin = new Odin();
        favoritesService.cancelFavor(id);
        return odin;
    }

}
