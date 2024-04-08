package com.example.llmauthentication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.llmauthentication.mapper")
public class LlmAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LlmAuthenticationApplication.class, args);
    }

}
