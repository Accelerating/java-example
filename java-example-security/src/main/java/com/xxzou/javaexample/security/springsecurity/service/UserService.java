package com.xxzou.javaexample.security.springsecurity.service;

import com.xxzou.javaexample.security.springsecurity.domain.LoginUser;
import com.xxzou.javaexample.security.springsecurity.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService implements UserDetailsService {


    private Map<String, UserInfo> userInfoMap = new ConcurrentHashMap<>();
    public UserService(){

        for (int i = 1; i <= 3; i++) {
            String username = "user"+i;
            String password = "$2a$10$Rt6oV8zGvjGiY.BvmnrBWuf/XHa2TLnuYx8Oajq89K1uFN1ll1ShK";
            userInfoMap.put(username, new UserInfo((long) i, username, password, new Date(), new Date(), 1));
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoMap.get(username);
        if(userInfo == null){
            throw new RuntimeException("用户名或密码错误");
        }
        return new LoginUser(userInfo);
    }
}
