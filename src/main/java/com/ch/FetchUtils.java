package com.ch;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by Devid on 2016/10/25.
 */
public final class FetchUtils {
    private static final Logger logger = Logger.getLogger(FetchUtils.class);
    
    private static Connection getJsoupConnection(String url){
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080));
        Connection connection = Jsoup.connect(url).proxy(proxy);
        connection.timeout(10000);
        return connection;
    }
    
    private static CloseableHttpResponse getHttpGet(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        HttpHost proxy = new HttpHost("localhost", 1080);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        get.setConfig(config);
        CloseableHttpResponse response = httpclient.execute(get);
        return response;
    }
    
    public static Document getByUrl(String url) throws IOException {
        logger.info("request url: " + url);
        Document document = getJsoupConnection(url).ignoreContentType(true).get();
        return document;
    }

    public static String httpGet(String url) throws IOException {
        return IOUtils.toString(getHttpGet(url).getEntity().getContent(), "utf-8");
    }

}
