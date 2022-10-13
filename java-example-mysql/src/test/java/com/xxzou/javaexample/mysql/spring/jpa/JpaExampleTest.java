package com.xxzou.javaexample.mysql.spring.jpa;

import com.xxzou.javaexample.mysql.entity.PermissionInfo;
import com.xxzou.javaexample.mysql.spring.jpa.repository.PermissionJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 17:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaExampleTest {

    @Autowired
    private PermissionJpaRepository permissionJpaRepository;

    @Test
    public void testJPQL(){
        List<PermissionInfo> permissions = permissionJpaRepository.findUserPermissionsByJPQL(1L);
        System.out.println(permissions);
    }

    @Test
    public void testSQL(){
        List<Object[]> permissions = permissionJpaRepository.findUserPermissionsBySQL(1L);
        System.out.println(permissions);
    }

}
