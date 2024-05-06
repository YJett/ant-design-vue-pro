package com.example.llmauthentication.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.listener.KpKnowledgePointListener;
import com.example.llmauthentication.mapper.SchInfoMapper;
import com.example.llmauthentication.pojo.ComInfo;
import com.example.llmauthentication.pojo.KpKnowledgePoint;
import com.example.llmauthentication.pojo.SchInfo;
import com.example.llmauthentication.service.KpKnowledgePointService;
import com.example.llmauthentication.mapper.KpKnowledgePointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Override
    public void importKpKonwledgeData(MultipartFile file, String schoolName) {
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
}




