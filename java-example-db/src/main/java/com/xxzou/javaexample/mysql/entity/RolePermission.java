package com.xxzou.javaexample.mysql.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author zxx
 * @date 2022/10/13 17:09
 */
@Entity
public class RolePermission {
    @Id
    private Long id;
    private Long roleId;
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
