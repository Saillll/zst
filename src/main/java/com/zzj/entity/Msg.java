package com.zzj.entity;

import java.util.Date;

public class Msg {
    private String id;

    private String type;

    private String source;

    private String target;

    private String involvedtype;

    private String involvedid;

    private String content;

    private Integer read;

    private Date createdate;

    private User sourceUser;

    private User targetUser;

    private Article involvedArticle;

    private Articlecomment involvedArticlecomment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getInvolvedtype() {
        return involvedtype;
    }

    public void setInvolvedtype(String involvedtype) {
        this.involvedtype = involvedtype == null ? null : involvedtype.trim();
    }

    public String getInvolvedid() {
        return involvedid;
    }

    public void setInvolvedid(String involvedid) {
        this.involvedid = involvedid == null ? null : involvedid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Article getInvolvedArticle() {
        return involvedArticle;
    }

    public void setInvolvedArticle(Article involvedArticle) {
        this.involvedArticle = involvedArticle;
    }

    public Articlecomment getInvolvedArticlecomment() {
        return involvedArticlecomment;
    }

    public void setInvolvedArticlecomment(Articlecomment involvedArticlecomment) {
        this.involvedArticlecomment = involvedArticlecomment;
    }

    public User getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
        this.sourceUser = sourceUser;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }
}