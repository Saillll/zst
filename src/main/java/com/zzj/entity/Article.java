package com.zzj.entity;

import java.util.Date;

public class Article extends  BaseEntity{
    private String id;

    private String title;

    private String brief;

    private String writer;

    private Date createdate;

    private Date editdate;

    private Integer viewnums;

    private Integer likenums;

    private String content;

    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer == null ? null : writer.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }

    public Integer getViewnums() {
        return viewnums;
    }

    public void setViewnums(Integer viewnums) {
        this.viewnums = viewnums;
    }

    public Integer getLikenums() {
        return likenums;
    }

    public void setLikenums(Integer likenums) {
        this.likenums = likenums;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}