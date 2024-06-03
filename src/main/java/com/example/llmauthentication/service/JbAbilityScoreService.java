package com.example.llmauthentication.service;

import com.example.llmauthentication.pojo.JbAbilityScore;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author arthur
* @description 针对表【jb_ability_score】的数据库操作Service
* @createDate 2024-06-01 16:20:45
*/
public interface JbAbilityScoreService extends IService<JbAbilityScore> {
    List<Map<String, Object>> getAverageScoreByType(Integer schId, Integer studentId);

    List<Map<String, Object>> getScoreAndKnowledgeName(Integer schId, Integer studentId, String type);
}
