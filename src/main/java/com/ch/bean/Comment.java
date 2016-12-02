package com.ch.bean;

import java.util.Date;
import java.util.List;

/**
 * 评论者
 * Created by Devid on 2016/12/2.
 */
public class Comment {
    private String id;
    private String username;
    private String content;
    private String cmtId;   // 评论id
    private Date cmtTime; // 评论时间
    private List<LikeUser> likeUsers;   // 喜欢的用户

    public Comment(String id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }

    public Comment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCmtId() {
        return cmtId;
    }

    public void setCmtId(String cmtId) {
        this.cmtId = cmtId;
    }

    public Date getCmtTime() {
        return cmtTime;
    }

    public void setCmtTime(Date cmtTime) {
        this.cmtTime = cmtTime;
    }

    public List<LikeUser> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<LikeUser> likeUsers) {
        this.likeUsers = likeUsers;
    }
}
