package com.xxzou.javaexample.elasticsearch.client.example.doc;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import com.xxzou.javaexample.elasticsearch.client.example.entity.ExampleDoc;
import com.xxzou.javaexample.elasticsearch.utils.JsonUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class UpdateDocumentExample {

    public static void main(String[] args) throws IOException {
        try (RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()) {


            UpdateRequest indexRequest = new UpdateRequest();
            indexRequest.index("example").id("1678185786431");
            //局部修改
            indexRequest.doc(XContentType.JSON, "name", "样例文档1修改");

            UpdateResponse updateResponse = client.update(indexRequest, RequestOptions.DEFAULT);
            System.out.println(updateResponse.getResult());

        }
    }

}
