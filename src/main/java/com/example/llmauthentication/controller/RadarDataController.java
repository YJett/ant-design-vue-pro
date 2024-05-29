package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.dto.AbilityScore;
import com.example.llmauthentication.pojo.StuAbilityScore;
import com.example.llmauthentication.service.StuAbilityScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/radarData")
public class RadarDataController {
    @Resource
    private StuAbilityScoreService stuAbilityScoreService;

    @GetMapping("/getAbilityScores")
    public Result<List<AbilityScore>> getAbilityScores(
            @RequestParam(value = "jobId") Integer jobId,
            @RequestParam(value = "schId") Integer schId,
            @RequestParam(value = "studentId") String studentId,
            @RequestParam(value = "lv") Integer lv,
            @RequestParam(value = "upabilityId", required = false) String upabilityId) {

        List<AbilityScore> stuAbilityScores = stuAbilityScoreService.getAbilityScores(jobId, schId, studentId, lv, upabilityId);
        return Result.success(stuAbilityScores);
    }
}
