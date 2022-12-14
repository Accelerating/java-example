package com.xxzou.javaexample.mysql.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author zxx
 * @date 2022/10/13 16:37
 */
@Entity
public class RoleInfo {

    @Id
    private Long id;
    private String roleName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
