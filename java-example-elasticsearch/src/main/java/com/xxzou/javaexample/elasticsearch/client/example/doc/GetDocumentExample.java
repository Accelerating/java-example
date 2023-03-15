package com.xxzou.javaexample.elasticsearch.client.example.doc;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class GetDocumentExample {

    public static void main(String[] args) throws IOException {

        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            GetRequest getRequest = new GetRequest();
            getRequest.index("example").id("1678185786431");
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println(getResponse);
            System.out.println(getResponse.getSource());
            System.out.println(getResponse.getSourceAsString());
        }

    }

}
