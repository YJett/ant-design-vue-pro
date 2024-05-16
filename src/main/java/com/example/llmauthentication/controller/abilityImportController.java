package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.service.impl.abilityDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class abilityImportController {
    @Autowired
    private abilityDataService dataService;

    @PostMapping("api/importAbilityData")
    public Result importData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.failed("文件传输失败");
        }

        try {
            dataService.importData(file);
            return Result.success();
        } catch (IOException e) {
            return Result.failed("IO异常");
        }
    }
}
