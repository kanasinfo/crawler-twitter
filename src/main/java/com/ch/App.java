package com.ch;


import com.ch.bean.TwitterMain;
import com.google.gson.Gson;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        
        String userId = "jason5ng32";
        String twitterId = "687951546491908097";
        String url = String.format("https://twitter.com/%s/status/%s", userId, twitterId);
        TwitterMain twitterMain = new TwitterMain(userId, twitterId);
        
        FetchService fetchService = new FetchService();
        Element element = FetchUtils.getByUrl(url).body();

        twitterMain = fetchService.fetchMain(element, twitterMain);
        System.out.println("正文：");
        System.out.println(new Gson().toJson(twitterMain));
        fetchService.fetchComment(element);
    }
}
