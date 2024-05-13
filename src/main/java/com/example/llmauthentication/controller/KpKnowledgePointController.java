package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.mapper.JbAbilityKnowledgeMapper;
import com.example.llmauthentication.mapper.KpKnowledgePointMapper;
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
@RequestMapping("api/knowledge")
public class KpKnowledgePointController {
    @Autowired
    private KpKnowledgePointService kpKnowledgePointService;
    @Autowired
    private SchInfoMapper schInfoMapper;
    @Autowired
    private KpKnowledgePointMapper kpKnowledgePointMapper;
    @Autowired
    private JbAbilityKnowledgeMapper jbAbilityKnowledgeMapper;
    @PostMapping("/importKpKnowledgeData")
    public Result<Integer> importKpKnowledgeData(@RequestParam("file") MultipartFile file,@RequestParam("schoolName") String schoolName){
        //监听器的版本
        //kpKnowledgePointService.importKpKonwledgeData(file,schoolName);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("schName",schoolName);
        SchInfo schInfo = schInfoMapper.selectOne(queryWrapper);
        Integer schId = schInfo.getSchId();

        //插入前先删除原来的数据
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("schId",schId);
        kpKnowledgePointMapper.delete(queryWrapper1);
        jbAbilityKnowledgeMapper.delete(queryWrapper1);


        try {
            kpKnowledgePointService.importKnowledgeData(file, schId);
            return Result.success();
        } catch (IOException e) {
            return Result.failed("导入失败");
        }

    }
}
