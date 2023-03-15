package com.xxzou.javaexample.elasticsearch.client.example.doc;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class DeleteDocumentExample {

    public static void main(String[] args) throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){

            DeleteRequest deleteRequest = new DeleteRequest();
            deleteRequest.index("example").id("1678185786431");
            DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
            System.out.println(deleteResponse);
        }
    }

}
