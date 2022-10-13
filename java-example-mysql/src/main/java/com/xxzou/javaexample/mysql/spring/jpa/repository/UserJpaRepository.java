package com.xxzou.javaexample.mysql.spring.jpa.repository;

import com.xxzou.javaexample.mysql.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 16:05
 */
public interface UserJpaRepository extends JpaRepository<UserInfo, Long> {

    List<UserInfo> findByRoleId(Long roleId);

}
