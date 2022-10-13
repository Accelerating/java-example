package com.xxzou.javaexample.mysql.spring.jdbc.repository;

import com.xxzou.javaexample.mysql.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 14:30
 */
@Repository
public interface UserJdbcRepository extends CrudRepository<UserInfo, Long> {

    List<UserInfo> findByAgeGreaterThanEqual(int age);


}
