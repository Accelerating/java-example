package com.xxzou.javaexample.security.springsecurity.entity;


import java.util.Date;

public class UserInfo {

    private Long id;
    private String username;
    private String password;
    private Date ctime;
    private Date utime;
    private Integer status;

    public UserInfo(){}

    public UserInfo(Long id, String username, String password, Date ctime, Date utime, Integer status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ctime = ctime;
        this.utime = utime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
