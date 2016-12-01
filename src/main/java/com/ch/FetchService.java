package com.ch;

import com.google.gson.Gson;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Devid on 2016/11/28.
 */
public class FetchService {
    private static final Logger logger = Logger.getLogger(FetchService.class);
    private static final String domain = "https://twitter.com";
    /**
     * 获取正文
     */
    public String fetchMain(Element element){
        String twitterContent = element.select("div.permalink-tweet-container").select("div.js-tweet-text-container").text();
        return twitterContent;
    }

    /**
     * 获取评论
     */
    public void fetchComment(Element element){
        Element cmtEle = element.select("div.stream-container").first();
        String minPosition = cmtEle.attr("data-min-position");
        logger.info("min-position: " + minPosition);
        ListIterator<Element> elements = cmtEle.select("li.ThreadedConversation,div.ThreadedConversation--loneTweet").listIterator();
       
        System.out.println("====");
        while (elements.hasNext()) {
            Element e = elements.next();
            getMainComment(e);
        }
    }

    private void getMainComment(Element e) {
        if (e.hasClass("ThreadedConversation")){    // 带显示更多
            ListIterator<Element> userEles = e.select("div.ThreadedConversation-tweet ").listIterator();
            while (userEles.hasNext()) {
                Element userEle = userEles.next();
                getCmtUser(userEle);
            }
            Elements showMoreEles = e.select("li.ThreadedConversation-showMore");
            if(showMoreEles != null && showMoreEles.size() > 0) {
                String moreLink = showMoreEles.first().attr("data-expansion-url");
                String moreUrl = domain + moreLink;
                logger.info("moreUrl: " + moreUrl);
                try {
                    String cmtJson = FetchUtils.httpGet(moreUrl);
                    Map cmtMap = new Gson().fromJson(cmtJson, Map.class);
                    Object conversation = cmtMap.get("conversation_html");
                    if (conversation != null) {
                        String conversationHtml = conversation.toString();
                        Document document = Jsoup.parse(conversationHtml);
                        Elements elements = document.select("li.ThreadedConversation");
                        if(elements != null && elements.size() > 0){
                            ListIterator<Element> iterator = elements.listIterator();
                            while (iterator.hasNext())
                                getMainComment(iterator.next());
                        }
                        
                    }
                    
                    
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
        if(e.hasClass("ThreadedConversation--loneTweet")){  // 只有单条评论
            getCmtUser(e);
        }
    }

    private void getCmtUser(Element e){
        String user = e.select("strong.fullname").text();
        System.out.print(user);
        System.out.println(" 说：");
        String cmt = e.select("div.js-tweet-text-container").select("p.js-tweet-text").text();
        System.out.println(cmt);
        System.out.println("----");
    }
}
