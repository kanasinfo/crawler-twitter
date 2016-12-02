package com.ch.bean;

import java.util.List;

/**
 * 推文主内容
 * Created by Devid on 2016/12/2.
 */
public class TwitterMan {
    private String twitterId;   // 推文ID
    private String username;    // 用户名
    private String userId;      // 用户ID
    private String Content;     // 推文内容
    private String pushTime;    // 发布时间
    private List<TwitterMan> tweetUsers;       // 转推人列表
    private List<Comment> comments; //所有评论
    private List<TwitterMan> likeUsers;        // 喜欢人列表
    

    public TwitterMan() {
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public List<TwitterMan> getTweetUsers() {
        return tweetUsers;
    }

    public void setTweetUsers(List<TwitterMan> tweetUsers) {
        this.tweetUsers = tweetUsers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<TwitterMan> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<TwitterMan> likeUsers) {
        this.likeUsers = likeUsers;
    }
}
