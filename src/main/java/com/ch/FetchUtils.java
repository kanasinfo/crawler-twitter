package com.ch;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Devid on 2016/10/25.
 */
public final class FetchUtils {
    
    private static final Logger logger = Logger.getLogger(FetchUtils.class);
    public static Document getByUrl(String url) throws IOException {
        logger.info("request url: " + url);
        Connection connection = Jsoup.connect(url);
        connection.timeout(10000);
        Document document = connection.ignoreContentType(true).get();
        return document;
    }

    public static String httpGet(String moreUrl) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet(moreUrl);
        CloseableHttpResponse response = httpclient.execute(get);
        return IOUtils.toString(response.getEntity().getContent(), "utf-8");
    }

    public static void main(String[] args) throws IOException {
        String content = FetchUtils.httpGet("https://twitter.com/i/threaded_conversation/687972884321562625");
        System.out.println(content);
    }
}
