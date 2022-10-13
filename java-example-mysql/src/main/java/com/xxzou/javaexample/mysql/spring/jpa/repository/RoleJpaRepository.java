package com.xxzou.javaexample.mysql.spring.jpa.repository;

import com.xxzou.javaexample.mysql.entity.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zxx
 * @date 2022/10/13 16:40
 */
public interface RoleJpaRepository extends JpaRepository<RoleInfo, Long> {
}
