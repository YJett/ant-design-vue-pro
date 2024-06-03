package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.service.impl.JbAbilityScoreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/abilityScore")
public class JbAbilityScoreController {

    @Autowired
    private JbAbilityScoreServiceImpl jbAbilityScoreService;

    @GetMapping("/averageScore")
    public Result getAverageScoreByType(@RequestParam Integer schId, @RequestParam Integer studentId) {
        List<Map<String, Object>> averageScores = jbAbilityScoreService.getAverageScoreByType(schId, studentId);
        return Result.success(averageScores);
    }

    @GetMapping("/scoreAndKnowledgeName")
    public Result getScoreAndKnowledgeName(@RequestParam Integer schId,
                                           @RequestParam Integer studentId,
                                           @RequestParam String type) {
        List<Map<String, Object>> result = jbAbilityScoreService.getScoreAndKnowledgeName(schId, studentId, type);
        return Result.success(result);
    }
}