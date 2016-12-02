package com.ch.bean;

import java.util.List;

/**
 * 推文主内容
 * Created by Devid on 2016/12/2.
 */
public class TwitterMain {
    private String twitterId;   // 推文ID
    private String userId;      // 用户ID
    private String Content;     // 推文内容
    private String pushTime;    // 发布时间
    private List<TweetUser> tweetUsers;       // 转推人列表
    private List<Comment> comments; //所有评论
    private List<LikeUser> likeUsers;        // 喜欢人列表
    public TwitterMain(String userId, String twitterId) {
        this.twitterId = twitterId;
        this.userId = userId;
    }

    public TwitterMain() {
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
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

    public List<TweetUser> getTweetUsers() {
        return tweetUsers;
    }

    public void setTweetUsers(List<TweetUser> tweetUsers) {
        this.tweetUsers = tweetUsers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<LikeUser> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<LikeUser> likeUsers) {
        this.likeUsers = likeUsers;
    }
}
