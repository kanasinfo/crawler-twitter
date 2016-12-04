package com.ch.bean;

import java.util.List;

/**
 * Created by Devid on 2016/12/4.
 */
public class HashTag {
    private String tag;
    private String url;
    private List<TwitterMan> twitterMen;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TwitterMan> getTwitterMen() {
        return twitterMen;
    }

    public void setTwitterMen(List<TwitterMan> twitterMen) {
        this.twitterMen = twitterMen;
    }
}
