package com.example.llmauthentication.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.llmauthentication.mapper.ComInfoMapper;
import com.example.llmauthentication.pojo.ComInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.security.auth.Subject;

@Component
public class comInfolistener extends AnalysisEventListener<ComInfo> {
    @Autowired
    private ComInfoMapper comInfoMapper;
    @Override
    public void invoke(ComInfo comInfo, AnalysisContext analysisContext) {

        comInfoMapper.insert(comInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
