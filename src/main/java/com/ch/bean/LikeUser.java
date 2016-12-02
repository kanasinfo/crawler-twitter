package com.ch.bean;

/**
 * 喜欢者
 * Created by Devid on 2016/12/2.
 */
public class LikeUser {
    private String id;
    private String name;

    public LikeUser() {
    }

    public LikeUser(String id, String name) {
        this.id = id;
        this.name = name;
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
