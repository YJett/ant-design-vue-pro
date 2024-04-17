package com.example.llmauthentication.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.llmauthentication.mapper.ComInfoMapper;
import com.example.llmauthentication.mapper.SchInfoMapper;
import com.example.llmauthentication.pojo.ComInfo;
import com.example.llmauthentication.pojo.SchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchInfoListener extends AnalysisEventListener<SchInfo> {
    @Autowired
    private SchInfoMapper schInfoMapper;

    @Override
    public void invoke(SchInfo schInfo, AnalysisContext analysisContext) {
        schInfoMapper.insert(schInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
