package com.ch;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ListIterator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        Connection connection = Jsoup.connect("https://twitter.com/i/jason5ng32/conversation/687951546491908097?include_available_features=1&include_entities=1&max_position=AgDVw82hlgkBcBZ5sSuMCQCQ1g9AYZMJAXAWDMJYjAkAINWivXnnCQBg1ApE1JQJASDVYUW0kgkB8BSFekOSCQFAVIlCII0JAdBUN107jAkBAJZ2CC-MCQBAlHVMK4wJAaCUFZspjAkAMBT6IimMCQAgVJCKMesKBKAUF307VQoBMFRpaQvBCQIQFMD-kLsJ&reset_error_state=false");
        connection.timeout(10000);
        Document document = connection.ignoreContentType(true).get();

        System.out.println(document.toString());
        /*String twitterContent = document.body().select("div.permalink-tweet-container").select("div.js-tweet-text-container").text();
        ListIterator<Element> elements = document.body().select("div.stream").select("div.content").listIterator();
        System.out.println("正文：");
        System.out.println(twitterContent);
        System.out.println("====");
        while (elements.hasNext()) {
            Element element = elements.next();
            System.out.print(element.select("strong.fullname").text());
            System.out.println(" 说：");
            System.out.println(element.select("div.js-tweet-text-container").select("p.js-tweet-text").text());
            System.out.println("----");
        }*/
    }
}
