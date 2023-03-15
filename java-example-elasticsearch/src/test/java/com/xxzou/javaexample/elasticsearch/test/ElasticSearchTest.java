package com.xxzou.javaexample.elasticsearch.test;


import com.xxzou.javaexample.elasticsearch.spring.entity.Book;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchRestTemplate esTemplate;


    @Test
    public void createIndex(){
        esTemplate.indexOps(Book.class).create();
    }

    @Test
    public void deleteIndex(){
        esTemplate.indexOps(Book.class).delete();
    }

    @Test
    public void createDoc(){
        Book book = new Book();
        book.setBookId("123456");
        book.setName("Brave New World");
        book.setAuthor(Collections.singletonList("Aldous Leonard Huxley"));
        book.setDate(LocalDate.now());
        book.setIntro("The astonishing novel Brave New World, originally published in 1932, presents Aldous Huxley's vision of the future--of a world utterly transformed");
        book.setPrice(20.0d);
        book.setSales(404);
        esTemplate.save(book);
    }

    @Test
    public void batchCreateDoc(){
        List<IndexQuery> queries = new ArrayList<>();
        IndexQuery iq1 = new IndexQuery();
        iq1.setObject(new Book("4ee82462","Dive into the Linux kernel architecture", Collections.singletonList("Wolfgang Mauerer"),
                "The content is comprehensive and in-depth, appreciate the infinite scenery of the Linux kernel.",
                19.9d,LocalDate.of(2010,6,1), "linux publisher",100));
        queries.add(iq1);

        IndexQuery iq2 = new IndexQuery();
        iq2.setObject(new Book("4ee82463","A Brief History Of Time",Collections.singletonList("Stephen Hawking"),
                "A fascinating story that explores the secrets at the heart of time and space.",
                9.9d,LocalDate.of(1988,1,1), "science publisher",10000));
        queries.add(iq2);

        IndexQuery iq3 = new IndexQuery();
        List<String> authors3 = new ArrayList<>();
        authors3.add("Neil Matthew");
        authors3.add("Richard Stones");
        iq3.setObject(new Book("4ee82464","Beginning Linux Programming 4th Edition", authors3,
                "Describes the Linux system and other UNIX-style operating system on the program development",
                12.9d,LocalDate.of(2010,6,1), "linux publisher",198));
        queries.add(iq3);


        IndexQuery iq4 = new IndexQuery();
        iq4.setObject(new Book("4ee82465","Linux Programming",Collections.singletonList("Richard Stones"),
                "Happy to Linux Programming",
                10.9d,LocalDate.of(2022,6,1), "linux publisher",102));
        queries.add(iq4);

        IndexQuery iq5 = new IndexQuery();
        iq5.setObject(new Book("4ee82466","Linus Autobiography",Collections.singletonList("Linus"),
                "Linus Autobiography",
                14.9d,LocalDate.of(2012,6,1), "autobiography publisher",140));
        queries.add(iq5);

        IndexQuery iq6 = new IndexQuery();
        iq6.setObject(new Book("4ee82467","Kobe Bryant Autobiography",Collections.singletonList("Kobe Bryant"),
                "Kobe Bryant Autobiography",
                20.9d,LocalDate.of(2019,8,1), "linux publisher",100));
        queries.add(iq6);

        IndexQuery iq7 = new IndexQuery();
        iq7.setObject(new Book("4ee82468","Theory of relativity",Collections.singletonList("Albert Einstein"),
                "Theory of relativity",
                18.9d,LocalDate.of(1905,8,1), "science publisher",9800));
        queries.add(iq7);

        esTemplate.bulkIndex(queries, Book.class);
    }


    @Test
    public void matchQuery(){
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("intro","fascinating");

        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQueryBuilder).build();
        SearchHits<Book> searchResult = esTemplate.search(query, Book.class);

        List<SearchHit<Book>> searchHits = searchResult.getSearchHits();
        for (SearchHit<Book> hit : searchHits) {
            Book book = hit.getContent();
            System.out.println(book);
        }

    }

    @Test
    public void deleteDocById(){
        esTemplate.delete("MgUWoIUBkH78p2H6i6hf", Book.class);
    }

    @Test
    public void deleteDocByQuery(){
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name","linux");
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQueryBuilder).build();
        esTemplate.delete(query, Book.class);
    }

    @Test
    public void updateDocById(){
        //two ways

        //①
//        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("intro","fascinating");
//
//        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQueryBuilder).build();
//        SearchHit<Book> hit = esTemplate.searchOne(query, Book.class);
//        Book content = hit.getContent();
//        content.setName("hello");
//        esTemplate.save(content);


        // ②
        Document document = Document.create();
        document.setId("QAVCoIUBkH78p2H6M6jK");
        document.put("intro","hello world!");
        UpdateQuery updateQuery = UpdateQuery.builder("QAVCoIUBkH78p2H6M6jK").withDocument(document).build();
        IndexCoordinates bookIndex = IndexCoordinates.of("books");
        esTemplate.update(updateQuery, bookIndex);


    }


}
