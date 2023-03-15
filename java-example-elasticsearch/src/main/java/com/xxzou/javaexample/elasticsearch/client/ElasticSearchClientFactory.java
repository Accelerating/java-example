package com.xxzou.javaexample.elasticsearch.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ElasticSearchClientFactory {

    private static final String ES_HOST = "192.168.152.137";
    private static final int ES_PORT = 9200;

    public static  RestHighLevelClient getHighLevelClient(){
        return getHighLevelClient(ES_HOST, ES_PORT);
    }

    public static RestHighLevelClient getHighLevelClient(String host, int port){
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port))
        );
        return esClient;
    }

    public static void main(String[] args) throws IOException {
        RestHighLevelClient highLevelClient = getHighLevelClient("192.168.152.137", 9200);
        System.out.println(highLevelClient);
        highLevelClient.close();
    }
}
