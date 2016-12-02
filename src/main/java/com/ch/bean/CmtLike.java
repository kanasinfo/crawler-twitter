package com.ch.bean;

/**
 * 评论喜欢信息
 * Created by Devid on 2016/12/2.
 */
public class CmtLike {
    private String userId;
    private String username;

    public CmtLike() {
    }

    public CmtLike(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
