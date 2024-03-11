package com.example.llmauthentication.service;


import com.example.llmauthentication.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService; // 假设您有一个UserService来处理用户信息

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauthUser = super.loadUser(userRequest);
        Map<String, Object> attributes = oauthUser.getAttributes();
        log.info("current user attr is {}",attributes);
        User user = userService.registerOrLoadUser(attributes);



        // 获取用户角色和权限
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();


        // 返回一个包含用户权限信息的OAuth2User对象
        return new DefaultOAuth2User(
                authorities,
                attributes,
                "userId"); // 假设userId是用户的唯一标识字段
    }
}

