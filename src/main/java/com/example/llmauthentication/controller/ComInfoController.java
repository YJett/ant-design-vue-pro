package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.llmauthentication.common.result.PageResult;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.ComInfoVo;
import com.example.llmauthentication.pojo.ComInfo;
import com.example.llmauthentication.service.ComInfoService;
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
@RequestMapping("api/com")
public class ComInfoController {
    @Resource
    private ComInfoService comInfoService;
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> credentials) {
        String comName = credentials.get("comName");
        String password = credentials.get("password");
        Result result = comInfoService.login(comName,password);
        return result;
    }

    @GetMapping("/page")
    public PageResult<ComInfo> getComPage(
            @RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "comName", required = false) String comName) {

        log.info("current params are pageNum: {}, pageSize: {}, email: {}, comName: {}", currentPage, pageSize, email, comName);
        Page<ComInfo> result = comInfoService.getComPage(currentPage, pageSize, email, comName);
        log.info("current list num is {}", result.getTotal());

        return PageResult.success(result);
    }


    @PostMapping("/create")
    public Result createCom(@RequestBody @Valid ComInfoVo comInfoVo) {
        boolean result = comInfoService.createCom(comInfoVo);
        return Result.judge(result);
    }
    @PutMapping("/update")
    public Result updateCom(@RequestBody @Valid ComInfoVo comInfoVo) {
        boolean updateResult = comInfoService.updateCom(comInfoVo);
        return Result.judge(updateResult);
    }



    @DeleteMapping("/{id}")
    public Result deleteCom(@PathVariable Long id) {
        boolean result = comInfoService.deleteCom(id);
        return Result.judge(result);
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

    @PostMapping("/importComData")
    public Result<Integer> importcomData(@RequestParam("file") MultipartFile file){
            comInfoService.importData(file);
            return Result.success();
    }


}
