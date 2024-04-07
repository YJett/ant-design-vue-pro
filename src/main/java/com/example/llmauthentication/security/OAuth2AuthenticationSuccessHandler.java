package com.example.llmauthentication.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // 假设我们使用OAuth2User的"userid"属性作为用户名
        String userId = oAuth2User.getAttribute("userId");

        // 使用JwtUtil生成JWT
        String token = jwtUtil.generateToken(userId);

        // 创建Cookie并设置JWT作为其值
        Cookie cookie = new Cookie("Authorization",  token);
        // 设置Cookie的路径。如果您希望Cookie对整个应用都有效，可以设置为"/"
        cookie.setPath("/");
        // 根据需要设置Cookie的有效期，这里假设为7天
        cookie.setMaxAge(5 * 24 * 60 * 60);
        // 仅允许HTTP访问此Cookie，增加安全性
        // cookie.setHttpOnly(true);
        response.addCookie(cookie);
        log.info("oauth2成功设置cookie完成");
        response.sendRedirect("/");

    }
}

