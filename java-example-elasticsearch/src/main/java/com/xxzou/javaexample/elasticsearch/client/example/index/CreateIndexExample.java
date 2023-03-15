package com.xxzou.javaexample.elasticsearch.client.example.index;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public class CreateIndexExample {

    public static void main(String[] args) throws IOException {
        try (RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("example");
            CreateIndexResponse response = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            if (response.isAcknowledged()) {
                System.out.println("create index successfully");
            }
        }

    }

}
