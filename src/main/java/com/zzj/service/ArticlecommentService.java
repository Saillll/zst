package com.zzj.service;

import com.google.common.collect.Lists;
import com.zzj.entity.Articlecomment;
import com.zzj.entity.ArticlecommentExample;
import com.zzj.mapper.ArticlecommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/1/9.
 */
@Slf4j
@Service
public class ArticlecommentService {

    @Autowired
    private ArticlecommentMapper articlecommentMapper;


    public List<Articlecomment> getCommentsByArticleID(String articleID){
        List<Articlecomment> articlecommentList = Lists.newArrayList();
        ArticlecommentExample articlecommentExample = new ArticlecommentExample();
        ArticlecommentExample.Criteria criteria = articlecommentExample.createCriteria();
        criteria.andArticleidEqualTo(articleID);
        articlecommentExample.setOrderByClause("createdate asc");
        articlecommentList = articlecommentMapper.selectByExample(articlecommentExample);
        return articlecommentList;
    }

    public Articlecomment getCommentByID (String id){
        Articlecomment articlecomment = new Articlecomment();
        articlecomment = articlecommentMapper.selectByPrimaryKey(id);
        return articlecomment;
    }
    public void save(Articlecomment articlecomment){
        articlecommentMapper.insert(articlecomment);
    }

}
