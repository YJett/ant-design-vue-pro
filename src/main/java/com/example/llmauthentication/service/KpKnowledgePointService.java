package com.example.llmauthentication.service;

import com.example.llmauthentication.pojo.KpKnowledgePoint;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author arthur
* @description 针对表【kp_knowledge_point】的数据库操作Service
* @createDate 2024-04-30 14:25:10
*/
public interface KpKnowledgePointService extends IService<KpKnowledgePoint> {


    void importKpKnowledgeData(MultipartFile file, String schoolName);

    void importKnowledgeData(MultipartFile file, Integer schId) throws IOException;
}
