//package com.example.llmauthentication.controller;
//
//import cn.dev33.satoken.stp.StpUtil;
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelWriter;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.example.llmauthentication.common.result.PageResult;
//import com.example.llmauthentication.common.result.Result;
//import com.example.llmauthentication.demos.web.UserTest;
//
//import com.example.llmauthentication.model.User;
//import com.example.llmauthentication.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLEncoder;
//
//@Slf4j
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("api/users")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
//    @RequestMapping("doLogin")
//    public String doLogin(String username, String password) {
//        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
//        if("zhang".equals(username) && "123456".equals(password)) {
//            StpUtil.login(10001);
//            return "登录成功";
//        }
//        return "登录失败";
//    }
//    @PostMapping("/login")
//    public Result<UserTest> login(String username, String password) {
//        log.info("user name is {} pwd is {}",username,password);
//        UserTest curuser = new UserTest();
//        curuser.setToken("123456");
//        curuser.setName(username);
//        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
//        if("zhang".equals(username) && "123456".equals(password)) {
//            StpUtil.login(10001);
//            return Result.success(curuser);
//        }
//        return Result.success(curuser);
//    }
//
//    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
//    @RequestMapping("isLogin")
//    public String isLogin() {
//        return "当前会话是否登录：" + StpUtil.isLogin();
//    }
//
//
//
//    @GetMapping("/page")
//    public PageResult<User> getUserPage(
//            @RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
//            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
//            @RequestParam(value = "externalUserId", required = false) String externalUserId,
//            @RequestParam(value = "username", required = false) String username,
//            @RequestParam(value = "canAccess", required = false) Integer canAccess) { // 新增参数
//
//        log.info("current params are pageNum: {}, pageSize: {}, externalUserId: {}, username: {}, canAccess: {}", currentPage, pageSize, externalUserId, username, canAccess);
//        IPage<User> result = userService.selectUserPage(currentPage, pageSize, externalUserId, username, canAccess); // 传递新参数
//        log.info("current list num is {}", result.getTotal());
//        return PageResult.success(result);
//    }
//
//
//    @PostMapping("/upload")
//    public Result<Integer> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        if (!file.isEmpty()) {
//'//            int rst = userService.saveOrUpdateFromExcel(file.getInputStream());
//'//            return Result.success(rst);
//        } else {
//            throw new IllegalArgumentException("File is empty");
//        }
//    }
//
//    @GetMapping("/{externalUserId}")
//    public Result<User> getUserByExternalUserId(@PathVariable String externalUserId) {
//        User user = userService.findByExternalUserId(externalUserId);
//        return Result.success(user);
//    }
//
//    @PostMapping("/")
//    public Result<Void> createUser(@RequestBody User user) {
//        userService.insertUser(user);
//        return Result.success();
//    }
//
//    @PutMapping("/{studentId}/access")
//    public Result<Void> updateCanAccess(@PathVariable String studentId, @RequestParam("canAccess") int canAccess) {
//        userService.updateCanAccess(studentId, canAccess);
//        return Result.success();
//    }
//    /*
//    @Async
//    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
//    @GetMapping("/template")
//    public DeferredResult<Void> downloadTemplate(HttpServletResponse response) {
//        DeferredResult<Void> deferredResult = new DeferredResult<>();
//
//        CompletableFuture.runAsync(() -> {
//            try {
//                String fileName = "用户导入模板.xlsx";
//                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//
//                String fileClassPath = "excel-templates" + File.separator + fileName;
//                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);
//
//                ServletOutputStream outputStream = response.getOutputStream();
//                ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
//
//                excelWriter.finish();
//                deferredResult.setResult(null); // 设置DeferredResult的结果，表示处理完成
//            } catch (IOException e) {
//                deferredResult.setErrorResult(e); // 处理异常情况
//            }
//        });
//
//        return deferredResult;
//    }
//     */
//    @GetMapping("/template")
//    public void downloadTemplate(HttpServletResponse response) throws IOException {
//        String fileName = "用户导入模板.xlsx";
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//
//        String fileClassPath = "excel-templates" + File.separator + fileName;
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);
//
//        ServletOutputStream outputStream = response.getOutputStream();
//        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
//
//        excelWriter.finish();
//    }
//
//
//    @DeleteMapping("/{externalUserId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String externalUserId) {
//        userService.deleteUser(externalUserId);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//}
