package com.ch;

import com.ch.bean.TweetUser;
import com.ch.bean.TwitterMain;
import com.ch.utils.GsonUtils;
import com.ch.utils.StringKit;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by Devid on 2016/11/28.
 */
public class FetchService {
    private static final Logger logger = Logger.getLogger(FetchService.class);
    private static final String domain = "https://twitter.com";
    private static final String MORE_CMT_URL = "https://twitter.com/i/jason5ng32/conversation/687951546491908097?include_available_features=1&include_entities=1&max_position=%s&reset_error_state=false";
    
    
    /**
     * 获取内容正文
     */
    public TwitterMain fetchMain(Element element, TwitterMain twitterMain){
        String twitterContent = element.select("div.permalink-tweet-container").select("div.js-tweet-text-container").text();
        String pushTime = element.select("div.permalink-tweet-container").select("div.client-and-actions > span.metadata > span").text();
        twitterMain.setContent(twitterContent);
        twitterMain.setPushTime(pushTime);
        twitterMain.setTweetUsers(fetchTweetUsers(twitterMain.getTwitterId()));
        return twitterMain;
    }

    /**
     * 获取转推人的列表
     * @param twitterId
     * @return
     */
    private List<TweetUser> fetchTweetUsers(String twitterId) {
        List<TweetUser> tweetUsers = new ArrayList<>();
        String tweetUrl = "https://twitter.com/i/activity/retweeted_popup?id=%s";
        try {
            String contentJson = FetchUtils.httpGet(String.format(tweetUrl, twitterId));
            Map<String, String> map = GsonUtils.getGson().fromJson(contentJson, Map.class);
            String html = StringKit.toString(map.get("htmlUsers"));
            ListIterator<Element> iterator = Jsoup.parse(html).body().select("ol.activity-popup-users").select("li.js-stream-item").listIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String username = element.select("strong.fullname ").text();
                String userId = element.select("a.js-user-profile-link").attr("href").replace("/", "");
                TweetUser user = new TweetUser(userId, username);
                tweetUsers.add(user);
            }
        } catch (IOException e) {
            logger.info("获取转推人列表失败", e);
        }
        return tweetUsers;
    }

    /**
     * 获取列表评论
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

        // TODO 获取异步的数据
        if (StringUtils.isNotBlank(minPosition)) {
            try {
                String moreJson = FetchUtils.httpGet(String.format(MORE_CMT_URL, minPosition));
                
            } catch (IOException e) {
                e.printStackTrace();
            }
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
