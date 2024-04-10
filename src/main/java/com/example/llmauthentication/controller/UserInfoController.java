package com.example.llmauthentication.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.demos.web.UserTest;
import com.example.llmauthentication.service.UserInfoService;
import com.example.llmauthentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;
    @PostMapping("/users/login")
    public Result login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        Result result = userInfoService.login(username,password);
        return result;
    }

    @GetMapping("/Account/QueryAllUsers")
    public Result queryAllUsers(
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "page", defaultValue = "1") int pagenum
    ) {
        Result allUserinfo = userInfoService.queryAllUsers(pagenum,limit);

        return allUserinfo;
    }
}
