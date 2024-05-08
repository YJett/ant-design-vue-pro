package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.mapper.SchInfoMapper;
import com.example.llmauthentication.pojo.SchInfo;
import com.example.llmauthentication.service.KpKnowledgePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/konwledge")
public class KpKonwledgePointController {
    @Autowired
    private KpKnowledgePointService kpKnowledgePointService;
    @Autowired
    private SchInfoMapper schInfoMapper;
    @PostMapping("/importKpKonwledgeData")
    public Result<Integer> importKpKonwledgeData(@RequestParam("file") MultipartFile file,@RequestParam("schoolName") String schoolName){
        //监听器的版本
        //kpKnowledgePointService.importKpKonwledgeData(file,schoolName);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("schName",schoolName);
        SchInfo schInfo = schInfoMapper.selectOne(queryWrapper);
        Integer schId = schInfo.getSchId();
        try {
            kpKnowledgePointService.importKnowledgeData(file, schId);
            return Result.success();
        } catch (IOException e) {
            return Result.failed("导入失败");
        }


    }
}
