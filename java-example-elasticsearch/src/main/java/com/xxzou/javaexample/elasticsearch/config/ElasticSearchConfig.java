package com.xxzou.javaexample.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;


@Configuration
public class ElasticSearchConfig {


    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.152.137", 9200)));
        return client;
    }


    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(@Autowired RestHighLevelClient restHighLevelClient){
        ElasticsearchRestTemplate elasticsearchRestTemplate = new ElasticsearchRestTemplate(restHighLevelClient);
        ElasticsearchConverter elasticsearchConverter = elasticsearchRestTemplate.getElasticsearchConverter();
        return elasticsearchRestTemplate;
    }

}
