package com.xxzou.javaexample.javase.stream.entity;


/**
 * @author zxx
 * @date 2022/10/10 17:54
 */
public class UserInfo {

    private Long id;
    private String name;
    private Integer age;

    public UserInfo() {
        System.out.println("create new user");
    }

    public UserInfo(Long id, String name, Integer age) {
        this();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString(){
        return this.name;
    }
}
