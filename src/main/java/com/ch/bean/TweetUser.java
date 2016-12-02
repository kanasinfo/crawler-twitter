package com.ch.bean;

/**
 * 转推人
 * Created by Devid on 2016/12/2.
 */
public class TweetUser {
    private String id;
    private String name;

    public TweetUser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TweetUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
