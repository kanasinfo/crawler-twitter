package com.ch;


import com.ch.bean.Comment;
import com.ch.bean.TwitterMain;
import com.google.gson.Gson;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        
        String userId = "jason5ng32";
        String twitterId = "687951546491908097";
        String url = String.format("https://twitter.com/%s/status/%s", userId, twitterId);
        TwitterMain twitterMain = new TwitterMain(userId, twitterId);
        
        FetchTwitterService fetchTwitterService = new FetchTwitterService();
        Element element = FetchUtils.getByUrl(url).body();

        twitterMain = fetchTwitterService.fetchMain(element, twitterMain);
        System.out.println("正文：");
        System.out.println(new Gson().toJson(twitterMain));
        List<Comment> comments = new ArrayList<>();
        fetchTwitterService.fetchComment(element, comments);
        twitterMain.setComments(comments);
    }
}
