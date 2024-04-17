package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.llmauthentication.common.result.PageResult;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.SchInfo;
import com.example.llmauthentication.pojo.SchInfoVo;
import com.example.llmauthentication.service.SchInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/sch")
public class SchInfoController {
    @Resource
    private SchInfoService schInfoService;
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> credentials) {
        String schName = credentials.get("schName");
        String password = credentials.get("password");
        Result result = schInfoService.login(schName,password);
        return result;
    }

    @GetMapping("/page")
    public PageResult<SchInfo> getSchPage(
            @RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "schName", required = false) String schName) {

        log.info("current params are pageNum: {}, pageSize: {}, email: {}, schName: {}", currentPage, pageSize, email, schName);
        Page<SchInfo> result = schInfoService.getSchPage(currentPage, pageSize, email, schName);
        log.info("current list num is {}", result.getTotal());

        return PageResult.success(result);
    }


    @PostMapping("/create")
    public Result createSch(@RequestBody @Valid SchInfoVo schInfoVo) {
        boolean result = schInfoService.createSch(schInfoVo);
        return Result.judge(result);
    }
    @PutMapping("/update")
    public Result updateSch(@RequestBody @Valid SchInfoVo schInfoVo) {
        boolean updateResult = schInfoService.updateSch(schInfoVo);
        return Result.judge(updateResult);
    }




    @DeleteMapping("/{id}")
    public Result deleteSch(@PathVariable Long id) {
        boolean result = schInfoService.deleteSch(id);
        return Result.judge(result);
    }

    @PutMapping("/batchsuccess")
    public Result successBatchSch(@RequestBody List<Long> ids) {
        boolean result = schInfoService.successBatchSch(ids);
        return Result.judge(result);
    }

    @PutMapping("/batchdelete")
    public Result deleteBatchSch(@RequestBody List<Long> ids) {
        boolean result = schInfoService.deleteBatchSch(ids);
        return Result.judge(result);
    }

    @PostMapping("/importSchInfoData")
    public Result<Integer> importSchInfoData(@RequestParam("file") MultipartFile file){
        schInfoService.importData(file);
        return Result.success();
    }



}

