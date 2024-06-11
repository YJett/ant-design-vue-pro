package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.pojo.StudentInfo;
import com.example.llmauthentication.pojo.StudentQueryParams;
import com.example.llmauthentication.service.StudentInfoService;
import com.example.llmauthentication.mapper.StudentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author arthur
* @description 针对表【student_info】的数据库操作Service实现
* @createDate 2024-05-19 15:17:34
*/
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo>
    implements StudentInfoService{

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    public List<Map<String, Object>> getStudentInfo(StudentQueryParams params) {


        return studentInfoMapper.getStudentInfo(params);
    }

    @Override
    public void getNewData() {
        studentInfoMapper.callSF_INS_ABILITY_ALL();
        studentInfoMapper.callSF_INS_KNOWLEDGE_ALL();

    }

}




