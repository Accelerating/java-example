package com.xxzou.javaexample.mysql.mybatis.mapper;

import com.xxzou.javaexample.mysql.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 11:21
 */
public interface UserInfoMapper {

    @Select("select * from user_info where id = #{userId} ")
    UserInfo findUserById(@Param("userId")Long userId);

    @Select("<script> " +
            "select * from user_info " +
            "<if test='minAge != null'>" +
            "where age > #{minAge} " +
            "</if>" +
            "</script>")
    List<UserInfo> findUserByAge(@Param("minAge") Integer minAge);

}
