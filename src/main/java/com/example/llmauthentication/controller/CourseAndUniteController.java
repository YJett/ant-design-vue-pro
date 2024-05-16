package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.mapper.*;
import com.example.llmauthentication.pojo.SchInfo;
import com.example.llmauthentication.service.CourseInfoService;
import com.example.llmauthentication.service.KpKnowledgePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/courseandunit")
public class CourseAndUniteController {
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private CourseInfoMapper courseInfoMapper;
    @Autowired
    private UnitInfoMapper unitInfoMapper;
    @Autowired
    private SchInfoMapper schInfoMapper;

    @PostMapping("/importdata")
    public Result<Integer> importData(@RequestParam("file") MultipartFile file,@RequestParam("schoolName") String schoolName){
        //监听器的版本
        //kpKnowledgePointService.importKpKonwledgeData(file,schoolName);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("schName",schoolName);
        SchInfo schInfo = schInfoMapper.selectOne(queryWrapper);
        Integer schId = schInfo.getSchId();

        //插入前先删除原来的数据
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("schId",schId);
        courseInfoMapper.delete(queryWrapper1);
        // todo 单元信息删除
        //unitInfoMapper.delete(queryWrapper1);


        try {
            courseInfoService.importCourseData(file, schId);
            //todo 单元信息导入
            return Result.success();
        } catch (IOException e) {
            return Result.failed("导入失败");
        }

    }
}
