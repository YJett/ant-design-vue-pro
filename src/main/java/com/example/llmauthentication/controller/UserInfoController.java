package com.example.llmauthentication.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.llmauthentication.common.result.PageResult;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.demos.web.UserTest;
import com.example.llmauthentication.pojo.UserInfo;
import com.example.llmauthentication.pojo.UserInfoVo;
import com.example.llmauthentication.service.UserInfoService;
import com.example.llmauthentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/users")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        Result result = userInfoService.login(username,password);
        return result;
    }

    @GetMapping("/page")
    public PageResult<UserInfo> getUserPage(
            @RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "userName", required = false) String userName) {

        log.info("current params are pageNum: {}, pageSize: {}, email: {}, userName: {}", currentPage, pageSize, email, userName);
        Page<UserInfo> result = userInfoService.getUserPage(currentPage, pageSize, email, userName);
        log.info("current list num is {}", result.getTotal());

        return PageResult.success(result);
    }


    @PostMapping("/create")
    public Result createUser(@RequestBody @Valid UserInfoVo userInfoVo) {
        boolean result = userInfoService.createUser(userInfoVo);
        return Result.judge(result);
    }
    @PutMapping("/update")
    public Result updateUser(@RequestBody @Valid UserInfoVo userInfoVo) {
        boolean updateResult = userInfoService.updateUser(userInfoVo);
        return Result.judge(updateResult);
    }



    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        boolean result = userInfoService.deleteUser(id);
        return Result.judge(result);
    }

}
