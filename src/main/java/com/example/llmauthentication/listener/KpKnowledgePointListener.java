package com.example.llmauthentication.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.llmauthentication.mapper.ComInfoMapper;
import com.example.llmauthentication.mapper.JbAbilityKnowledgeMapper;
import com.example.llmauthentication.mapper.KpKnowledgePointMapper;
import com.example.llmauthentication.pojo.ComInfo;
import com.example.llmauthentication.pojo.JbAbilityKnowledge;
import com.example.llmauthentication.pojo.KpKnowledgePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


public class KpKnowledgePointListener extends AnalysisEventListener<KpKnowledgePoint> {
    private Integer schId;
    @Resource
    private KpKnowledgePointMapper kpKnowledgePointMapper;
    @Resource
    private JbAbilityKnowledgeMapper jbAbilityKnowledgeMapper;

    @Override
    public void invoke(KpKnowledgePoint kpKnowledgePoint, AnalysisContext analysisContext) {

        kpKnowledgePoint.setSchid(schId);
        kpKnowledgePointMapper.insert(kpKnowledgePoint);
        Integer knowledgeid = kpKnowledgePoint.getKnowledgeid();
        Integer uplevel = kpKnowledgePoint.getUplevel();
        JbAbilityKnowledge jbAbilityKnowledge = new JbAbilityKnowledge();
        jbAbilityKnowledge.setSchid(schId);
        jbAbilityKnowledge.setAbilityid(knowledgeid);
        jbAbilityKnowledge.setKnowledgeid(uplevel);
        jbAbilityKnowledgeMapper.insert(jbAbilityKnowledge);


    }

    public KpKnowledgePointListener(Integer schId) {
        this.schId = schId; // 外部数据传入监听器
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }



}
