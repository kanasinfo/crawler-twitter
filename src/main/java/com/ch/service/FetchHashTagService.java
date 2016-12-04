package com.ch.service;

import com.ch.FetchUtils;
import com.ch.bean.TwitterMan;
import com.ch.utils.GsonUtils;
import com.ch.utils.StringKit;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

/**
 * Created by Devid on 2016/12/4.
 */
public class FetchHashTagService {
    private static final Logger logger = Logger.getLogger(FetchHashTagService.class);
    private static int index = 1;

    public List<TwitterMan> fetchHashTag(String tag, Element element) {
        Element rootEle = element.select("div#timeline").first();
        String minPosition = rootEle.select("div.stream-container").attr("data-min-position");
        String maxPosition = rootEle.select("div.stream-container").attr("data-max-position");
        logger.info("max position: " + maxPosition);
        Element listEle = rootEle.select("ol.stream-items.js-navigable-stream").first();
        List<TwitterMan> twitterMen = new ArrayList<>();

        twitterMen.addAll(fetchList(listEle));
        try {
            fetchAsync(tag, maxPosition, twitterMen);
        } catch (IOException e) {
            logger.info("异步数据获取错误", e);
        }
        return twitterMen;
    }

    private List<TwitterMan> fetchList(Element rootEle) {
        ListIterator<Element> list = rootEle.select("li.js-stream-item.stream-item.stream-item").listIterator();
        List<TwitterMan> twitterMen = new ArrayList<>();
        while (list.hasNext()) {
            TwitterMan twitterMan = new TwitterMan();
            Element twitterEle = list.next();
            String twitterId = twitterEle.attr("data-item-id");
            String username = twitterEle.select("div.stream-item-header>a.account-group>strong.fullname").text();
            String userId = twitterEle.select("a.js-user-profile-link").attr("data-user-id");
            String account = twitterEle.select("a.js-user-profile-link").attr("href").replace("/", "");
            String content = twitterEle.select("div.js-tweet-text-container>p.TweetTextSize.js-tweet-text.tweet-text").text();
            String cmtTimeStr = twitterEle.select("span._timestamp").attr("data-time-ms");
            Date date = new Date(StringKit.toLong(cmtTimeStr));

            twitterMan.setUserId(userId);
            twitterMan.setUsername(username);
            twitterMan.setAccount(account);
            twitterMan.setTwitterId(twitterId);
            twitterMan.setContent(content);
            twitterMan.setPushTime(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
            twitterMen.add(twitterMan);
            logger.info("tag-twitterMan: " + GsonUtils.getGson().toJson(twitterMan));
        }
        return twitterMen;
    }

    private void fetchAsync(String tag, String maxPosition, List<TwitterMan> twitterMen) throws IOException {
        String url = "http://twitter.com/i/search/timeline?vertical=default&q=" + tag + "&include_available_features=1&include_entities=1&max_position=" + maxPosition + "&reset_error_state=false";
        String jsonContent = FetchUtils.httpGet(url);
        Map<String, Object> map = GsonUtils.getGson().fromJson(jsonContent, Map.class);
        Element element = Jsoup.parse(StringKit.toString(map.get("items_html")));
        List<TwitterMan> list = fetchList(element);
        twitterMen.addAll(list);
        logger.info("index: " + (index++));
        logger.info("Json Content: " + jsonContent);
        if (map.get("items_html") != null && StringKit.toInt(map.get("new_latent_count")) > 0 && StringKit.isNotBlank(StringKit.toString("items_html").trim())) {
            fetchAsync(tag, StringKit.toString(map.get("min_position")), twitterMen);
        }

    }
}
