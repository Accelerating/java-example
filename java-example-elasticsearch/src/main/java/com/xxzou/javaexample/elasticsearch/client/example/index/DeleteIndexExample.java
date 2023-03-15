package com.xxzou.javaexample.elasticsearch.client.example.index;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class DeleteIndexExample {

    public static void main(String[] args) throws IOException {
        try (RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()) {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("example");
            AcknowledgedResponse acknowledgedResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            if (acknowledgedResponse.isAcknowledged()) {
                System.out.println("delete index successfully");
            }
        }

    }

}
