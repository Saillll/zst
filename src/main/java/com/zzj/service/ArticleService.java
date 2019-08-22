package com.zzj.service;

import com.alibaba.fastjson.JSON;
import com.vdurmont.emoji.EmojiParser;
import com.zzj.entity.Article;
import com.zzj.entity.ArticleExample;
import com.zzj.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/15.
 */
@Slf4j
@Service
@SuppressWarnings(value = "all")
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public String findAll(){
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andIdIsNotNull();
        Article article=articleMapper.selectByPrimaryKey("1");
        List<Article> list = articleMapper.selectByExample(articleExample);
        //list.add(article);
        String json = JSON.toJSONString(list);
        return json;
    }
    public List<Article> findAllList(int index){
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
//        criteria.andIdIsNotNull();
//        Article article=articleMapper.selectByPrimaryKey("1");
        articleExample.setOrderByClause("a.createdate desc");
        articleExample.setPageIndex(index);
        List<Article> list = articleMapper.selectByExample(articleExample);
        //list.add(article);
        return list;
    }
    public List<Article> findAllListLimit(int index,String userID){
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
//        criteria.andIdIsNotNull();
        criteria.andWriterEqualTo(userID);
//        Article article=articleMapper.selectByPrimaryKey("1");
        articleExample.setOrderByClause("a.createdate desc");
        articleExample.setPageIndex(index);
        List<Article> list = articleMapper.selectByExample(articleExample);
        return list;
    }

    public Article getOne(String id){
        Article article = new Article();
        article = articleMapper.selectByPrimaryKey(id);
        if(article != null){
            article.setContent(EmojiParser.parseToUnicode(article.getContent()==null?"":article.getContent()));
            article.setBrief(EmojiParser.parseToUnicode(article.getBrief()==null?"":article.getBrief()));
            article.setTitle(EmojiParser.parseToUnicode(article.getTitle()==null?"":article.getTitle()));
        }
        return article;
    }

    public boolean deleteOne(String id){
        boolean done = true;
        if(StringUtils.isEmpty(id)){
            return done;
        }
        int deleteArticles = articleMapper.deleteByPrimaryKey(id);
        if(deleteArticles < 1){
            done = false;
        }
        return done;
    }
    public void save(Article article){
        Article duplicater = this.getOne(article.getId());
        if(ObjectUtils.isEmpty(duplicater)){
            article.setTitle(EmojiParser.parseToAliases(article.getTitle()==null?"":article.getTitle()));
            article.setBrief(EmojiParser.parseToAliases(article.getBrief()==null?"":article.getBrief()));
            article.setCreatedate(new Date());
            articleMapper.insert(article);
        }else{
            article.setEditdate(new Date());
            articleMapper.updateByPrimaryKeySelective(article);
        }
    }
    public void readOne(String id){
        if(StringUtils.isEmpty(id)){
            return;
        }
        Article article = new Article();
        article = articleMapper.selectByPrimaryKey(id);
        int viewnums = ObjectUtils.isEmpty(article.getViewnums()) ? 0 : article.getViewnums();
        article.setViewnums( viewnums + 1);
        // 浏览数 + 1
        articleMapper.updateByPrimaryKeySelective(article);
    }


}
