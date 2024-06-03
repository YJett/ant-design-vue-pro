package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.pojo.JbAbilityScore;
import com.example.llmauthentication.service.JbAbilityScoreService;
import com.example.llmauthentication.mapper.JbAbilityScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author arthur
* @description 针对表【jb_ability_score】的数据库操作Service实现
* @createDate 2024-06-01 16:20:45
*/
@Service
public class JbAbilityScoreServiceImpl extends ServiceImpl<JbAbilityScoreMapper, JbAbilityScore>
    implements JbAbilityScoreService{

    @Autowired
    private JbAbilityScoreMapper jbAbilityScoreMapper;
    @Override
    @Transactional
    public List<Map<String, Object>> getAverageScoreByType(Integer schId, Integer studentId) {
        // 调用存储过程
        jbAbilityScoreMapper.callSfInsKnowledge(schId, studentId);

        // 执行查询
        return jbAbilityScoreMapper.getAverageScoreByType(schId, studentId);
    }

    public List<Map<String, Object>> getScoreAndKnowledgeName(Integer schId, Integer studentId, String type) {
        return jbAbilityScoreMapper.getScoreAndKnowledgeName(schId, studentId, type);
    }
}




