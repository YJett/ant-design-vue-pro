package com.example.llmauthentication.security;

import com.example.llmauthentication.mapper.UserMapper;
import com.example.llmauthentication.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${security.permit-all-paths}")
    private String[] permitAllPathsArray;
    private List<String> permitAllPaths;

    private final JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostConstruct
    public void init() {
        permitAllPaths = Arrays.asList(permitAllPathsArray);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String userId = null;
        String jwt = null;
        String requestURI = request.getRequestURI();

        log.info("handle by jwtfilter Request URI: {}", requestURI);
        // 检查请求的URI是否在白名单中
        if (permitAllPaths.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            // 从请求中获取所有的Cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("Authorization".equals(cookie.getName())) {
                        jwt = cookie.getValue();
                        log.info("current jwt is {}",jwt);
                        if (jwt != null) {
                            userId = jwtUtil.getUserIdFromToken(jwt);
                            log.info("current user id is {}",userId);
                            break;
                        }
                    }
                }
            }
            if (jwt == null || userId == null || !jwtUtil.validateToken(jwt, userId)) {
                log.info("Invalid or expired token");
                throw new Exception("Invalid or expired token");
            }
            log.info("token is valid");
            // Existing authentication logic here
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                User currentUser = userMapper.findByExternalUserId(userId);
                log.info("current user is {}",currentUser);
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                log.info("user id length is {}",currentUser.getExternalUserId().length());
                if (currentUser.getExternalUserId().length() >= 10) {
                    authorities.add(new SimpleGrantedAuthority(Role.ROLE_STUDENT.name()));
                } else {
                    authorities.add(new SimpleGrantedAuthority(Role.ROLE_TEACHER.name()));
                }
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userId, null, authorities);
                log.info("current user id is{} authorities is {}",userId,authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(request, response);
    }


}

