package com.xxzou.javaexample.elasticsearch.client.example.doc;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import com.xxzou.javaexample.elasticsearch.client.example.entity.ExampleDoc;
import com.xxzou.javaexample.elasticsearch.utils.JsonUtils;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BatchDeleteDocumentExample {

    public static void main(String[] args) throws IOException {
        try (RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()) {

            List<DocWriteRequest<?>> requestList = new ArrayList<>();
            BulkRequest bulkRequest = new BulkRequest();
            for (int i = 0; i < 5; i++) {
                DeleteRequest deleteRequest = new DeleteRequest();
                deleteRequest.index("example").id(String.valueOf(i+1));
                requestList.add(deleteRequest);

            }
            bulkRequest.add(requestList);
            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

            System.out.println("cost time: " + bulkResponse.getTook());
            System.out.println(Arrays.toString(bulkResponse.getItems()));

        }
    }

}
