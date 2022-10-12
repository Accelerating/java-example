package com.xxzou.javaexample.javase.stream;

import com.xxzou.javaexample.javase.stream.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author zxx
 * @date 2022/10/10 17:09
 */
public class LazyInvokeExample {

    public static void main(String[] args) {

        Stream<UserInfo> userInfoStream = Stream.of(
                new UserInfo(1L, "user1", 15),
                new UserInfo(2L, "user2", 30),
                new UserInfo(3L, "user3", 45));

        //no output
        userInfoStream = userInfoStream.filter(user -> {
            System.out.println("filtering...");
            return user.getAge() > 20;
        });

        //only print “filtering...” twice
        Optional<UserInfo> optional = userInfoStream.findFirst();
        System.out.println(optional.get());
    }


}
