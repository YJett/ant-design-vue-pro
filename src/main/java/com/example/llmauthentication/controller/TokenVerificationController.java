package com.example.llmauthentication.controller;

import com.example.llmauthentication.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/verify-token")
public class TokenVerificationController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided");
        }
        try {
            String username = jwtUtil.getUsernameFromToken(token);
            boolean isValid = !jwtUtil.isTokenExpired(token);
            System.out.println(token + " " + username);
            if (isValid) {
                return ResponseEntity.ok().body("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired or invalid");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired or invalid");
        }
    }
}
