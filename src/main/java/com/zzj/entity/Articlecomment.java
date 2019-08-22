package com.zzj.entity;

import java.util.Date;

public class Articlecomment extends  BaseEntity{
    private String id;

    private String articleid;

    private String parentid;

    private String originuserid;

    private String originusername;

    private User user;

    private String comments;

    private Long likenums;

    private Date createdate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        setOriginusername(user.getNickname());
        setOriginuserid(user.getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid == null ? null : articleid.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getOriginuserid() {
        return originuserid;
    }

    public void setOriginuserid(String originuserid) {
        this.originuserid = originuserid == null ? null : originuserid.trim();
    }

    public String getOriginusername() {
        return originusername;
    }

    public void setOriginusername(String originusername) {
        this.originusername = originusername == null ? null : originusername.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Long getLikenums() {
        return likenums;
    }

    public void setLikenums(Long likenums) {
        this.likenums = likenums;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}