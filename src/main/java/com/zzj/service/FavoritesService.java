package com.zzj.service;

import com.alibaba.fastjson.JSON;
import com.vdurmont.emoji.EmojiParser;
import com.zzj.entity.*;
import com.zzj.mapper.ArticleMapper;
import com.zzj.mapper.FavoritesMapper;
import com.zzj.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/11/15.
 */
@Slf4j
@Service
@SuppressWarnings(value = "all")
public class FavoritesService {

    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    public Favorites getOne(String id){
        Favorites favorites = new Favorites();
        favorites = favoritesMapper.selectByPrimaryKey(id);
        return favorites;
    }

    public void insert(Favorites favorites){
        if(StringUtils.isEmpty(favorites.getId())){
           favorites.setId(UUID.randomUUID().toString());
        }
        favoritesMapper.insert(favorites);
    }
    public void favor(Favorites favorites){
        FavoritesExample favoritesExample = new FavoritesExample();
        FavoritesExample.Criteria criteria = favoritesExample.createCriteria();
//        criteria.andIdIsNotNull();
        criteria.andArticleidEqualTo(favorites.getArticleid());
        criteria.andUseridEqualTo(favorites.getUserid());
        List<Favorites> list = favoritesMapper.selectByExample(favoritesExample);
        if(list.size() > 0){
            favorites = list.get(0);
            favoritesMapper.deleteByPrimaryKey(favorites.getId());
        }else{
            favorites.setCreatedate(new Date());
            insert(favorites);
        }
    }
    public List<Favorites> findAllListLimit(int index,String userID){
        FavoritesExample favoritesExample = new FavoritesExample();
        FavoritesExample.Criteria criteria = favoritesExample.createCriteria();
        criteria.andUseridEqualTo(userID);
        favoritesExample.setOrderByClause("createdate desc");
        favoritesExample.setPageIndex(index);
        List<Favorites> list = favoritesMapper.selectByExample(favoritesExample);
        list = translater(list);
        return list;
    }

    public List<Favorites> translater(List<Favorites> list){
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            Favorites favorites = new Favorites();
            favorites = (Favorites)iterator.next();
            Article article = new Article();
            article = articleMapper.selectByPrimaryKey(favorites.getArticleid());
            User user = new User();
            user = userMapper.selectByPrimaryKey(favorites.getUserid());
            favorites.setArticle(article);
            favorites.setUser(user);
            if(ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(article)){
                iterator.remove();
            }
        }
        return list;
    }

    public boolean getLike(Favorites favorites){
        boolean hasLiked = false;
        FavoritesExample favoritesExample = new FavoritesExample();
        FavoritesExample.Criteria criteria = favoritesExample.createCriteria();
        criteria.andArticleidEqualTo(favorites.getArticleid());
        criteria.andUseridEqualTo(favorites.getUserid());
        List<Favorites> list = favoritesMapper.selectByExample(favoritesExample);
        if(list.size() > 0) {
            hasLiked = true;
        }
        return hasLiked;
    }

    public void cancelFavor(String id){
        favoritesMapper.deleteByPrimaryKey(id);
    }
}
