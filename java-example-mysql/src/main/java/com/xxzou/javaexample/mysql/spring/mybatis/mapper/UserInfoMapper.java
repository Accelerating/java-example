package com.xxzou.javaexample.mysql.spring.mybatis.mapper;

import com.xxzou.javaexample.mysql.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zxx
 * @date 2022/10/13 11:21
 */
@Mapper
public interface UserInfoMapper {


    List<UserInfo> findUserByName(@Param("nickname")String nickname);


}
