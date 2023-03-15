package com.xxzou.javaexample.elasticsearch.client.example.doc;

import com.xxzou.javaexample.elasticsearch.client.ElasticSearchClientFactory;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

public class QueryDocumentExample {

    public static void main(String[] args) throws Exception{

        //全量查询
//        queryAll();

        //条件查询
//        queryByCondition();

        //分页查询
//        queryPage();

        //排序查询
//        querySort();

        //查询指定字段
//        querySpecField();

        //多条件组合查询
//        multiConditionQuery();

        //范围查询
//        rangeQuery();

        //模糊查询
//        fuzzyQuery();

        //聚合查询
//        aggregationQuery();

        //分组聚合查询
        groupAggregationQuery();
    }


    private static void  queryAll() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");
            searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
            }
        }
    }

    private static void queryByCondition() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");
            //注意，对于term类型的查询，如果查询的是字符串，需要在字段后面加上.keyword才能精准匹配，如果是数字布尔类型的变量就不用
            searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name.keyword", "样例文档5")));
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
            }
        }
    }

    private static void queryPage() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");

            SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", "样例"));

            //从第零条开始查询两条
            query.from(0);
            query.size(2);


            searchRequest.source(query);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
            }
        }
    }

    private static void querySort() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");

            SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", "样例"));

            //从第零条开始查询两条
            query.from(0);
            query.size(2);

            //按照number字段倒叙
            query.sort("number", SortOrder.DESC);
            searchRequest.source(query);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
            }
        }
    }

    private static void querySpecField() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");

            SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", "样例"));

            //从第零条开始查询两条
            query.from(0);
            query.size(2);

            //按照number字段倒叙
            query.sort("number", SortOrder.DESC);


            //要求包含的字段
            String[] include = {"id", "name"};
            //要求排除的字段，可以传null
            String[] exclude = {"description"};
            query.fetchSource(include, exclude);

            searchRequest.source(query);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
            }
        }
    }


    private static void multiConditionQuery() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");


            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            //must表示且关系，类似于sql里面的and
            boolQuery.must(QueryBuilders.matchQuery("k", "v"));
            boolQuery.must(QueryBuilders.matchQuery("k", "v"));

            //should表示或关系，类似于sql里面的or
//            boolQuery.should(QueryBuilders.matchQuery("k", "v"));
//            boolQuery.should(QueryBuilders.matchQuery("k", "v"));


            SearchSourceBuilder query = new SearchSourceBuilder().query(boolQuery);


            searchRequest.source(query);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
            }
        }
    }


    private static void rangeQuery() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");

            //查询number大于3000小于6000的文档
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("number");
            rangeQuery.gt(3000);
            rangeQuery.lt(6000);

            SearchSourceBuilder query = new SearchSourceBuilder().query(rangeQuery);


            searchRequest.source(query);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
            }
        }

    }


    public static void fuzzyQuery() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");

            //表示namg里面含有指定值，允许相差一个字符
            FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("name", "样例文档").fuzziness(Fuzziness.ONE);


            SearchSourceBuilder query = new SearchSourceBuilder().query(fuzzyQuery);


            searchRequest.source(query);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
            }
        }
    }


    private static void aggregationQuery() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");

            SearchSourceBuilder query = new SearchSourceBuilder();
            //查询number的平均值
            AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("avgNumber").field("number");
            query.aggregation(avgAggregationBuilder);

            searchRequest.source(query);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            for (Aggregation aggregation : aggregations) {
                if(aggregation instanceof Avg){
                    double value = ((Avg) aggregation).getValue();
                    System.out.println(aggregation.getName() + "的平均值为:" + value);
                }
            }

        }
    }

    private static void groupAggregationQuery() throws IOException {
        try(RestHighLevelClient client = ElasticSearchClientFactory.getHighLevelClient()){
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("example");

            SearchSourceBuilder query = new SearchSourceBuilder();
            //按照number分组
            TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("numberGroup").field("number");
            query.aggregation(termsAggregationBuilder);

            searchRequest.source(query);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

            //获取分组数据
            for (Aggregation aggregation : response.getAggregations()) {
                //这个instanceof的用法是jdk16之后出现的语法糖，不用写强转
                if(aggregation instanceof ParsedLongTerms plt){
                    for (Terms.Bucket bucket : plt.getBuckets()) {
                        System.out.println(bucket.getKeyAsString() + ":" + bucket.getDocCount());
                    }
                }

            }

        }
    }

}
