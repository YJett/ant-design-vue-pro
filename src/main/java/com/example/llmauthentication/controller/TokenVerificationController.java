package com.example.llmauthentication.controller;

import com.example.llmauthentication.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/verify-token")
public class TokenVerificationController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        String token = null;
        // 从请求中获取所有的Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // 查找名为"Authorization"的Cookie
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    // 提取JWT令牌，去掉"Bearer "前缀
                    String cookieValue = cookie.getValue();
                    if (cookieValue != null ) {
                        token = cookieValue;
                        break;
                    }
                }
            }
        }
        if (token == null) {
            log.info("No token provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided");
        }
        try {
            String username = jwtUtil.getUsernameFromToken(token);
            boolean isValid = !jwtUtil.isTokenExpired(token);
            if (isValid) {
                log.info("The user {} has logged in . current token is {}", username,token );
                return ResponseEntity.ok().body("Token is valid");
            } else {
                log.info("The user {}  logged failed . current token is {}", username,token );
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired or invalid");
            }
        } catch (Exception e) {
            log.info(" current token is {} attempt failed",token );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired or invalid");
        }
    }
}
