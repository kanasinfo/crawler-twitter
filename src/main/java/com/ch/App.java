package com.ch;


import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        String url = "https://twitter.com/jason5ng32/status/687951546491908097";
        FetchService fetchService = new FetchService();
        Element element = FetchUtils.getByUrl(url).body();

        String main = fetchService.fetchMain(element);
        System.out.println("正文：");
        System.out.println(main);
        fetchService.fetchComment(element);
    }
}
