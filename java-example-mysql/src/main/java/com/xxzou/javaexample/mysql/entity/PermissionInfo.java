package com.xxzou.javaexample.mysql.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author zxx
 * @date 2022/10/13 16:37
 */
@Entity
public class PermissionInfo {

    @Id
    private Long id;
    private String permissionName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return permissionName;
    }
}
