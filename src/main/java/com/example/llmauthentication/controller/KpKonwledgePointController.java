package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.service.KpKnowledgePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/konwledge")
public class KpKonwledgePointController {
    @Autowired
    private KpKnowledgePointService kpKnowledgePointService;

    @PostMapping("/importKpKonwledgeData")
    public Result<Integer> importKpKonwledgeData(@RequestParam("file") MultipartFile file,@RequestParam("schoolName") String schoolName){
        kpKnowledgePointService.importKpKonwledgeData(file,schoolName);
        return Result.success();
    }
}
