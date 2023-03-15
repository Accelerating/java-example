package com.xxzou.javaexample.elasticsearch.client.example.index;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class QueryIndexExample {

    public static void main(String[] args) throws IOException {
        try (RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()) {
            GetIndexRequest getIndexRequest = new GetIndexRequest("example");
            GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
            System.out.println(getIndexResponse.getAliases());
            System.out.println(getIndexResponse.getMappings());
            System.out.println(getIndexResponse.getSettings());
        }

    }

}
