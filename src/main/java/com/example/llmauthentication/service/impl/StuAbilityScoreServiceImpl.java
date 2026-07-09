package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.dto.AbilityScore;
import com.example.llmauthentication.mapper.StuAbilityScoreMapper;
import com.example.llmauthentication.pojo.StuAbilityScore;
import com.example.llmauthentication.service.StuAbilityScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StuAbilityScoreServiceImpl extends ServiceImpl<StuAbilityScoreMapper,StuAbilityScore> implements StuAbilityScoreService {

    @Autowired
    private StuAbilityScoreMapper asaMapper;

    public void executeProcedure(Integer IN_JOBID, Integer IN_SCHID, String IN_STUNO) {
        try {
            asaMapper.callSF_INS_ABILITY(IN_JOBID, IN_SCHID, IN_STUNO);
        } catch (BadSqlGrammarException ex) {
            if (isMissingAbilityProcedure(ex)) {
                log.warn("Skipping SF_INS_ABILITY because the procedure is missing. jobId={}, schId={}, studentId={}",
                        IN_JOBID, IN_SCHID, IN_STUNO);
                return;
            }
            throw ex;
        }
    }

    private boolean isMissingAbilityProcedure(BadSqlGrammarException ex) {
        Throwable cause = ex.getMostSpecificCause();
        String message = cause == null ? ex.getMessage() : cause.getMessage();
        return message != null && message.contains("SF_INS_ABILITY") && message.contains("does not exist");
    }


    @Override
    public List<AbilityScore> getAbilityScores(Integer jobId, Integer schId, String studentId, Integer lv, String upabilityId) {
        executeProcedure(jobId, schId, studentId);
        return asaMapper.getAbilityScores(jobId, schId, studentId, lv, upabilityId);
    }
}
