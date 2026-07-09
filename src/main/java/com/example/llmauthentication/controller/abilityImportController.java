package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.service.Neo4jSyncClient;
import com.example.llmauthentication.service.impl.abilityDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class abilityImportController {
    @Autowired
    private abilityDataService dataService;
    @Autowired
    private Neo4jSyncClient neo4jSyncClient;
    @PostMapping("api/importAbilityData")
    public Result importData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.failed("文件传输失败");
        }

        try {
            dataService.importData(file);
            neo4jSyncClient.syncFull("ability-import");
            return Result.success();
        } catch (IOException e) {
            return Result.failed("IO异常");
        }
    }
}
