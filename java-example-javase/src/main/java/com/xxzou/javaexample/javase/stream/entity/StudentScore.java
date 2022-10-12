package com.xxzou.javaexample.javase.stream.entity;

/**
 * @author zxx
 * @date 2022/10/10 18:39
 */
public class StudentScore {

    private String name;
    private Integer subject;
    private Integer score;

    public StudentScore(String name, Integer subject, Integer score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + "-" + subject + ":" + score;
    }
}
