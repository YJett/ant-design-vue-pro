package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.llmauthentication.common.result.PageResult;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.JbJob;
import com.example.llmauthentication.service.JbJobService;
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
@RequestMapping("api/job")
public class JobController {
    @Resource
    private JbJobService jbjobService;
    @GetMapping("/jobInfo")
    public Result<List<JbJob>> getJobInfo() {
        List<JbJob> jobInfoList = jbjobService.getAllJobInfo();
        return Result.success(jobInfoList);
    }

}
