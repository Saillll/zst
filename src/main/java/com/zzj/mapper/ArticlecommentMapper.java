package com.zzj.mapper;

import com.zzj.entity.Articlecomment;
import com.zzj.entity.ArticlecommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticlecommentMapper {
    int countByExample(ArticlecommentExample example);

    int deleteByExample(ArticlecommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Articlecomment record);

    int insertSelective(Articlecomment record);

    List<Articlecomment> selectByExample(ArticlecommentExample example);

    Articlecomment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Articlecomment record, @Param("example") ArticlecommentExample example);

    int updateByExample(@Param("record") Articlecomment record, @Param("example") ArticlecommentExample example);

    int updateByPrimaryKeySelective(Articlecomment record);

    int updateByPrimaryKey(Articlecomment record);
}