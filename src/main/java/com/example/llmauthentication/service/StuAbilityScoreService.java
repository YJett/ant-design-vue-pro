package com.example.llmauthentication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.llmauthentication.dto.AbilityScore;
import com.example.llmauthentication.pojo.StuAbilityScore;

import java.util.List;

public interface StuAbilityScoreService extends IService<com.example.llmauthentication.pojo.StuAbilityScore> {
    List<AbilityScore> getAbilityScores(Integer jobId, Integer schId, String studentId, Integer lv, String upabilityId);


}
