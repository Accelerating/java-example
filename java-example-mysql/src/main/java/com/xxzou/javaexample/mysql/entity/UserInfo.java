package com.xxzou.javaexample.mysql.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zxx
 * @date 2022/10/12 16:13
 */
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nickname;
    private Integer age;
    private Integer gender;
    private String email;
    private Long roleId;
    @Column(name = "create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                ", createTime=" + createTime +
                '}';
    }
}
