package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.llmauthentication.common.result.PageResult;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.UserInfo;
import com.example.llmauthentication.pojo.UserInfoVo;
import com.example.llmauthentication.service.UserInfoService;
import com.example.llmauthentication.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
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

    @GetMapping("/users/page")
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


    @PostMapping("/users/create")
    public Result createUser(@RequestBody @Valid UserInfoVo userInfoVo) {
        boolean result = userInfoService.createUser(userInfoVo);
        return Result.judge(result);
    }

    @PutMapping("/users/update")
    public Result<Boolean> updateUser(@RequestBody @Valid UserInfoVo userInfoVo) {
        log.info("receive userinfovo is {}",userInfoVo);
        boolean updateResult = userInfoService.updateUser(userInfoVo);
        return Result.judge(updateResult);
    }



    @DeleteMapping("/users/{id}")
    public Result deleteUser(@PathVariable Long id) {
        boolean result = userInfoService.deleteUser(id);
        return Result.judge(result);
    }

    @PostMapping("/users/regist")
    public Result regist(@RequestBody UserInfo user){
        Result result = userInfoService.regist(user);
        return result;
    }

    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String filePath = FileUtils.saveFileToLocal(file);
            return Result.success(filePath);
        } else {
            throw new IllegalArgumentException("File is empty");
        }
    }


}
