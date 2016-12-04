package com.ch;


import com.ch.bean.Comment;
import com.ch.bean.HashTag;
import com.ch.bean.TwitterMan;
import com.ch.service.FetchHashTagService;
import com.ch.service.FetchTwitterService;
import com.ch.utils.GsonUtils;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    private FetchTwitterService fetchTwitterService = new FetchTwitterService();

    public TwitterMan fetchTwitter(String account, String twitterId) throws IOException {
        String url = String.format("https://twitter.com/%s/status/%s", account, twitterId);
        TwitterMan twitterMan = new TwitterMan();
        twitterMan.setAccount(account);
        twitterMan.setTwitterId(twitterId);

        FetchTwitterService fetchTwitterService = new FetchTwitterService();
        Element element = FetchUtils.getByUrl(url).body();
        twitterMan = fetchTwitterService.fetchMain(element, twitterMan);
        List<Comment> comments = new ArrayList<>();
        fetchTwitterService.fetchComment(element, comments);
        twitterMan.setComments(comments);
        return twitterMan;
    }

    public HashTag fetchHashTag(String tag) throws IOException {
        HashTag hashTag = new HashTag();
        String url = String.format("http://twitter.com/hashtag/%s", URLEncoder.encode(tag, "utf-8"));
        hashTag.setTag(tag);
        hashTag.setUrl(url);
        Element element = FetchUtils.getByUrl(url);
        FetchHashTagService fetchHashTagService = new FetchHashTagService();
        List<TwitterMan> twitterMen = fetchHashTagService.fetchHashTag(URLEncoder.encode("#" + tag, "utf-8"), element);
        hashTag.setTwitterMen(twitterMen);
        return hashTag;
    }

    public static void main(String[] args) throws IOException {

        App app = new App();
        //TwitterMan twitterMan = app.fetchTwitter("jason5ng32", "687951546491908097");
        HashTag hashTag = app.fetchHashTag("MacBook");
        System.out.println(GsonUtils.getGson().toJson(hashTag));

    }
}
