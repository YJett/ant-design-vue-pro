package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.llmauthentication.mapper.*;
import com.example.llmauthentication.pojo.*;
import com.example.llmauthentication.service.StudentInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Autowired
    private ContestInfoMapper contestInfoMapper;

    @Autowired
    private CertificateInfoMapper certificateInfoMapper;

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

    @Override
    public WordInfo getWordInfo(Integer schId, String studentNo) {
        WordInfo wordInfo = new WordInfo();

        // 获取学生基本信息
        QueryWrapper<StudentInfo> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("schId", schId);
        studentQueryWrapper.eq("studentNo", studentNo);
        StudentInfo studentInfo = studentInfoMapper.selectOne(studentQueryWrapper);

        if (studentInfo != null) {
            BeanUtils.copyProperties(studentInfo, wordInfo);
        }

        // 获取专业核心课（去重），每行展示三个，最多展示6门
        QueryWrapper<CourseInfo> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("type", "专业核心课");
        courseQueryWrapper.eq("schId", schId);
        List<CourseInfo> courseInfos = courseInfoMapper.selectList(courseQueryWrapper);
        List<String> courseNames = new ArrayList<>();
        if (courseInfos != null && !courseInfos.isEmpty()) {
             courseNames = courseInfos.stream()
                    .map(course -> Optional.ofNullable(course.getCoursenm()).orElse(""))
                    .filter(name -> !name.isEmpty())
                    .distinct()
                    .limit(6)
                    .collect(Collectors.toList());
        }
        wordInfo.setCourseName(courseNames);

        // 获取竞赛信息（去重），每行展示一个
        QueryWrapper<ContestInfo> contestQueryWrapper = new QueryWrapper<>();
        contestQueryWrapper.eq("schId", schId);
        contestQueryWrapper.eq("studentNo", studentNo);
        List<ContestInfo> contestInfos = contestInfoMapper.selectList(contestQueryWrapper);
        List<String> contestNames = new ArrayList<>();
        if (contestInfos != null && !contestInfos.isEmpty()) {
            contestNames = contestInfos.stream()
                    .map(contest -> Optional.ofNullable(contest.getContestnm()).orElse(""))
                    .filter(name -> !name.isEmpty())
                    .distinct()
                    .collect(Collectors.toList());
        }
        wordInfo.setContestnm(contestNames);

        // 获取技能证书（去重），每行展示一个
        QueryWrapper<CertificateInfo> certificateQueryWrapper = new QueryWrapper<>();
        certificateQueryWrapper.eq("schId", schId);
        certificateQueryWrapper.eq("studentNo", studentNo);
        List<CertificateInfo> certificateInfos = certificateInfoMapper.selectList(certificateQueryWrapper);
        List<String> certificateNames = new ArrayList<>();
        if (certificateInfos != null && !certificateInfos.isEmpty()) {
            certificateNames = certificateInfos.stream()
                    .map(certificate -> Optional.ofNullable(certificate.getCertinm()).orElse(""))
                    .filter(name -> !name.isEmpty())
                    .distinct()
                    .collect(Collectors.toList());
        }
        wordInfo.setCertinm(certificateNames);

        return wordInfo;
    }



}




