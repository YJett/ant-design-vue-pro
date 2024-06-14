package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.mapper.StuCharacterScoreMapper;
import com.example.llmauthentication.pojo.StuCharacterScore;
import com.example.llmauthentication.pojo.StudentInfo;
import com.example.llmauthentication.pojo.StudentLiteracy;
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

    @Autowired
    private StuCharacterScoreMapper stuCharacterScoreMapper;

    public List<Map<String, Object>> getStudentInfo(StudentQueryParams params) {


        return studentInfoMapper.getStudentInfo(params);
    }

    @Override
    public void getNewData() {
        studentInfoMapper.callSF_INS_ABILITY_ALL();
        studentInfoMapper.callSF_INS_KNOWLEDGE_ALL();

    }

    @Override
    public StudentLiteracy getStudentLiteracy(Integer schId, String studentNo) {
        studentInfoMapper.callSFInsCharacter(schId,studentNo);
        QueryWrapper<StudentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("schId",schId);
        queryWrapper.eq("studentNo",studentNo);
        QueryWrapper<StuCharacterScore> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("schId",schId);
        queryWrapper1.eq("stuNo",studentNo);
        StudentLiteracy studentLiteracy = new StudentLiteracy();
        StudentInfo studentInfo = studentInfoMapper.selectOne(queryWrapper);
        StuCharacterScore stuCharacterScore = stuCharacterScoreMapper.selectOne(queryWrapper1);
        studentLiteracy.setStudentInfo(studentInfo);
        studentLiteracy.setStuCharacterScore(stuCharacterScore);
        return studentLiteracy;
    }

}




