package com.xxzou.javaexample.elasticsearch.client.example.doc;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import com.xxzou.javaexample.elasticsearch.client.example.entity.ExampleDoc;
import com.xxzou.javaexample.elasticsearch.utils.JsonUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class InsertDocumentExample {

    public static void main(String[] args) throws IOException {

        try (RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()) {
            ExampleDoc doc = new ExampleDoc();
            doc.setId(String.valueOf(System.currentTimeMillis()));
            doc.setName("样例文档1");
            doc.setDescription("样例文档1的描述信息");
            doc.setNumber(ThreadLocalRandom.current().nextInt(1000, 9999));

            String json = JsonUtils.toJson(doc);
            System.out.println(json);

            IndexRequest indexRequest = new IndexRequest();
            indexRequest.source(json, XContentType.JSON);
            indexRequest.index("example").id(doc.getId());
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println(indexResponse.getResult());

        }

    }

}
