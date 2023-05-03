package com.xxzou.javaexample.mysql.spring.jpa.repository;

import com.xxzou.javaexample.mysql.entity.PermissionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 16:57
 */
public interface PermissionJpaRepository extends JpaRepository<PermissionInfo, Long> {


    /**
     * jpql
     */
    @Query("from PermissionInfo pi" +
                    " inner join RolePermission rp on rp.permissionId = pi.id" +
                    " inner join UserInfo ui on ui.roleId = rp.roleId" +
                    " where ui.id = :userId")
    List<PermissionInfo> findUserPermissionsByJPQL(@Param("userId") Long userId);


    /**
     * sql
     */
    @Query(nativeQuery=true,
            value="select pi.* from permission_info pi" +
                    " inner join role_permission rp on rp.permission_id = pi.id" +
                    " inner join user_info ui on ui.role_id = rp.role_id" +
                    " where ui.id = :userId")
    List<Object[]> findUserPermissionsBySQL(@Param("userId") Long userId);
}
