package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.dto.AbilityScore;
import com.example.llmauthentication.mapper.StuAbilityScoreMapper;
import com.example.llmauthentication.pojo.StuAbilityScore;
import com.example.llmauthentication.service.StuAbilityScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuAbilityScoreServiceImpl extends ServiceImpl<StuAbilityScoreMapper,StuAbilityScore> implements StuAbilityScoreService {

    @Autowired
    private StuAbilityScoreMapper asaMapper;

    public void executeProcedure(Integer IN_JOBID, Integer IN_SCHID, String IN_STUNO) {
        asaMapper.callSF_INS_ABILITY(IN_JOBID, IN_SCHID, IN_STUNO);
    }


    @Override
    public List<AbilityScore> getAbilityScores(Integer jobId, Integer schId, String studentId, Integer lv, String upabilityId) {
        executeProcedure(jobId, schId, studentId);
        return asaMapper.getAbilityScores(jobId, schId, studentId, lv, upabilityId);
    }
}
