package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.llmauthentication.common.result.PageResult;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.model.User;
import com.example.llmauthentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/page")
    public PageResult<User> getUserPage(
            @RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "externalUserId", required = false) String externalUserId,
            @RequestParam(value = "username", required = false) String username) {

        log.info("current params is {} {} {}",currentPage,pageSize,externalUserId);
        IPage<User>  result =  userService.selectUserPage(currentPage, pageSize, externalUserId, username);
        log.info("current list num is {}",result.getTotal());
        return PageResult.success(result);
    }


    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            userService.saveOrUpdateFromExcel(file.getInputStream());
        } else {
            throw new IllegalArgumentException("File is empty");
        }
    }

    @GetMapping("/{externalUserId}")
    public Result<User> getUserByExternalUserId(@PathVariable String externalUserId) {
        User user = userService.findByExternalUserId(externalUserId);
        return Result.success(user);
    }

    @PostMapping("/")
    public Result<Void> createUser(@RequestBody User user) {
        userService.insertUser(user);
        return Result.success();
    }

    @PutMapping("/{studentId}/access")
    public Result<Void> updateCanAccess(@PathVariable String studentId, @RequestParam("canAccess") int canAccess) {
        userService.updateCanAccess(studentId, canAccess);
        return Result.success();
    }

    @DeleteMapping("/{externalUserId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String externalUserId) {
        userService.deleteUser(externalUserId);
        return ResponseEntity.ok().build();
    }


}
