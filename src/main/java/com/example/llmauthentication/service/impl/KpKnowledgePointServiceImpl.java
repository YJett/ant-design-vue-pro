package com.example.llmauthentication.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.listener.KpKnowledgePointListener;
import com.example.llmauthentication.mapper.JbAbilityKnowledgeMapper;
import com.example.llmauthentication.mapper.SchInfoMapper;
import com.example.llmauthentication.pojo.ComInfo;
import com.example.llmauthentication.pojo.JbAbilityKnowledge;
import com.example.llmauthentication.pojo.KpKnowledgePoint;
import com.example.llmauthentication.pojo.SchInfo;
import com.example.llmauthentication.service.KpKnowledgePointService;
import com.example.llmauthentication.mapper.KpKnowledgePointMapper;
import com.example.llmauthentication.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
* @author arthur
* @description 针对表【kp_knowledge_point】的数据库操作Service实现
* @createDate 2024-04-30 14:25:10
*/
@Service
public class KpKnowledgePointServiceImpl extends ServiceImpl<KpKnowledgePointMapper, KpKnowledgePoint>
    implements KpKnowledgePointService{
    @Autowired
    private SchInfoMapper schInfoMapper;
    @Autowired
    private KpKnowledgePointMapper kpKnowledgePointMapper;
    @Autowired
    private JbAbilityKnowledgeMapper jbAbilityKnowledgeMapper;

    @Override
    public void importKpKnowledgeData(MultipartFile file, String schoolName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("schName",schoolName);
        SchInfo schInfo = schInfoMapper.selectOne(queryWrapper);
        Integer schId = schInfo.getSchId();
        KpKnowledgePointListener kpKnowledgePointListener = new KpKnowledgePointListener(schId);
        try {
            EasyExcel.read(file.getInputStream(), KpKnowledgePoint.class,kpKnowledgePointListener).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void importKnowledgeData(MultipartFile file, Integer schId) throws IOException {
        List<KpKnowledgePoint> knowledgePoints = ExcelReader.readKnowledgePoints(file, schId);
        List<JbAbilityKnowledge> abilityKnowledgeList = ExcelReader.readAbilityKnowledge(file, schId);

        // 插入学校知识表
        for (KpKnowledgePoint knowledgePoint : knowledgePoints) {
            kpKnowledgePointMapper.insert(knowledgePoint);
        }

        // 插入能力知识映射表
        for (JbAbilityKnowledge abilityKnowledge : abilityKnowledgeList) {
            jbAbilityKnowledgeMapper.insert(abilityKnowledge);
        }
    }
}




