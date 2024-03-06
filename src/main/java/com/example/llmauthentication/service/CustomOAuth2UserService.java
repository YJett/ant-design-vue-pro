package com.example.llmauthentication.service;


import com.example.llmauthentication.model.EcnuUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User user = super.loadUser(userRequest);
        Map<String, Object> attributes = user.getAttributes();
        EcnuUser ecnuUser = new EcnuUser();
        ecnuUser.setUserId(String.valueOf(attributes.get("userId")));
        ecnuUser.setName((String) attributes.get("name"));
        ecnuUser.setVpnEnabled((Integer) attributes.get("vpnEnabled"));
        // 省略保存逻辑
        System.out.println(ecnuUser);
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes,
                "userId");
    }
}
