package com.xxzou.javaexample.mysql.jdbc;

import com.xxzou.javaexample.mysql.entity.UserInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zxx
 * @date 2022/10/12 14:30
 */
public class JdbcExample {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String username = "root";
        String password = "123456";
        MySQLConnection connection = new MySQLConnection(url, username, password);
        connection.connect();

        //query
        String querySql = "select * from user_info where age > ?";
        List<UserInfo> userList = connection.query(querySql, UserInfo.class, 20);
        System.out.println(userList);


        //update
        String insertSql = "INSERT INTO user_info (nickname, gender, age, email, create_time) VALUES (?, ?, ?, ?, now())";
        int effCount = connection.update(insertSql, "testuser", 1, "30", "testuser@email.com");
        System.out.println(effCount);


        //transactional
        connection.startTransaction();
        String updateSql = "update user_info set age = ? where nickname = ?";
        connection.update(updateSql, 10, "jack");
//        connection.rollback();
        connection.commit();
    }

}
