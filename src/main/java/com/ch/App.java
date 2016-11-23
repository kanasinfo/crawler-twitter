package com.ch;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        Connection connection = Jsoup.connect("https://twitter.com/jason5ng32/status/687951546491908097");
        connection.timeout(10000);
        Document document = connection.get();
        String twitterContent = document.body().select("div.permalink-tweet-container").select("div.js-tweet-text-container").text();

    }
}
