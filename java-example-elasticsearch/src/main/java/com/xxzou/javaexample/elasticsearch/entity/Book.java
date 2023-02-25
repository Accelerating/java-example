package com.xxzou.javaexample.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

@Document(indexName = "books")
public class Book {

    @Id
    private String id;

    @Field(name = "book_id", type = FieldType.Keyword)
    private String bookId;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(name = "author", type = FieldType.Keyword)
    private List<String> author;

    @Field(name = "intro", type = FieldType.Text)
    private String intro;

    @Field(name = "price", type = FieldType.Double)
    private Double price;

    @Field(name = "date", type = FieldType.Date, format = DateFormat.date)
    private LocalDate date;

    @Field(name = "publisher", type = FieldType.Keyword)
    private String publisher;

    @Field(name = "sales", type = FieldType.Integer)
    private Integer sales;

    public Book(){}

    public Book(String bookId, String name, List<String> author, String intro, Double price, LocalDate date, String publisher, Integer sales) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.intro = intro;
        this.price = price;
        this.date = date;
        this.publisher = publisher;
        this.sales = sales;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", intro='" + intro + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", publisher='" + publisher + '\'' +
                ", sales=" + sales +
                '}';
    }
}
