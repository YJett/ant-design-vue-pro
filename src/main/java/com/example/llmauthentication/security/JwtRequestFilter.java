package com.example.llmauthentication.security;

import com.example.llmauthentication.mapper.UserMapper;
import com.example.llmauthentication.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String userId = null;
        String jwt = null;

        // 从请求中获取所有的Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // 查找名为"Authorization"的Cookie
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    // 提取JWT令牌，去掉"Bearer "前缀
                    String cookieValue = cookie.getValue();
                    if (cookieValue != null) {
                        jwt = cookieValue;
                        userId = jwtUtil.getUserIdFromToken(jwt);
                        break;
                    }
                }
            }
        }
        if (!jwtUtil.validateToken(jwt, userId)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired or invalid");
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            User currentUser = userMapper.findByExternalUserId(userId);
            if (currentUser.getExternalUserId().length()>=10){
                authorities.add(new SimpleGrantedAuthority(Role.ROLE_STUDENT.name()));
            }else {
                authorities.add(new SimpleGrantedAuthority(Role.ROLE_TEACHER.name()));
            }
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userId, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        chain.doFilter(request, response);
    }
}

