package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.llmauthentication.mapper.*;
import com.example.llmauthentication.pojo.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * // TODO
 *
 * @author ranyouwei
 * @date 2024/5/20
 */
@Service
public class StudentInfoImportService {
    @Autowired
    private SchInfoMapper schInfoMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private StuGradeInfoMapper stuGradeInfoMapper;

    @Autowired
    private StuCourseDataMapper stuCourseDataMapper;

    @Autowired
    private ScholarshipInfoMapper scholarshipInfoMapper;

    @Autowired
    private WorkStudyInfoMapper workStudyInfoMapper;

    @Transactional
    public void importAllStudentData(MultipartFile file, String schoolName) throws IOException {
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {

            QueryWrapper<SchInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("schName",schoolName);
            SchInfo schInfo = schInfoMapper.selectOne(queryWrapper);
            Integer schId = schInfo.getSchId();

            // 导入学生基本信息
            importStudentBasicInfo(workbook.getSheet("学生基本信息"), schId,schoolName);
            // 导入成绩总揽
            //importStuGradeInfo(workbook.getSheet("成绩总揽"), schId);
            //导入课程详细信息
            //importStuCourseData(workbook.getSheet("课程详细信息"),schId);
            //导入奖学金
            //importScholarShip(workbook.getSheet("奖学金"),schId);
            //导入勤工助学记录
            //importWorkStudyInfo(workbook.getSheet("勤工助学记录"),schId);
            //导入卫生信息

        }
    }

    private void importWorkStudyInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                WorkStudyInfo workStudyInfo = new WorkStudyInfo();
                workStudyInfo.setSchid(schId);
                workStudyInfo.setStudentno(row.getCell(0).getStringCellValue());
                workStudyInfo.setName(row.getCell(1).getStringCellValue());
                workStudyInfo.setPrizes(row.getCell(2).getNumericCellValue());
                workStudyInfo.setUnit( row.getCell(3).getStringCellValue());
                workStudyInfo.setStartDate(row.getCell(4).getStringCellValue());
                workStudyInfo.setEndDate(row.getCell(5).getStringCellValue());
                workStudyInfo.setCreatetime(new Date());
                workStudyInfo.setUpdatetime(new Date());

                workStudyInfoMapper.insert(workStudyInfo);
            }
        }
    }

    private void importScholarShip(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                ScholarshipInfo scholarshipInfo = new ScholarshipInfo();
                scholarshipInfo.setSchid(schId);
                scholarshipInfo.setStudentno(row.getCell(0).getStringCellValue());
                scholarshipInfo.setName(row.getCell(1).getStringCellValue());
                scholarshipInfo.setScholarshipclass(row.getCell(2).getStringCellValue());
                scholarshipInfo.setLevel( row.getCell(3).getStringCellValue());
                scholarshipInfo.setPrizes(row.getCell(4).getNumericCellValue());
                scholarshipInfo.setYyyymm(row.getCell(5).getStringCellValue());
                scholarshipInfo.setCreatetime(new Date());
                scholarshipInfo.setUpdatetime(new Date());

                scholarshipInfoMapper.insert(scholarshipInfo);
            }
        }
    }

    private void importStudentBasicInfo(Sheet sheet, int schId,String schNmane) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setSchid(schId);
                studentInfo.setSchnm(schNmane);
                studentInfo.setDepartment(row.getCell(0).getStringCellValue());
                studentInfo.setMajor(row.getCell(1).getStringCellValue());
                studentInfo.setStudentclass(row.getCell(2).getStringCellValue());
                studentInfo.setStudentno( row.getCell(3).getStringCellValue());
                studentInfo.setStudentnm(row.getCell(4).getStringCellValue());
                studentInfo.setGender(row.getCell(5).getStringCellValue());
                studentInfo.setBirthday(row.getCell(6).getStringCellValue());
                studentInfo.setEnrollmentDate(row.getCell(7).getStringCellValue());
                studentInfo.setParty(row.getCell(8).getStringCellValue());
                studentInfo.setHometown(row.getCell(9).getStringCellValue());
                studentInfo.setHealth(row.getCell(10).getStringCellValue());
                studentInfo.setDisability(row.getCell(11).getStringCellValue());
                studentInfo.setCreatetime(new Date());
                studentInfo.setUpdatetime(new Date());


                studentInfoMapper.insert(studentInfo);
            }
        }
    }

    private void importStuGradeInfo(Sheet sheet, int schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                StuGradeInfo stuGradeInfo = new StuGradeInfo();
                stuGradeInfo.setSchid(schId);
                stuGradeInfo.setStudentno(row.getCell(0).getStringCellValue());
                stuGradeInfo.setGeneralCredit((int)row.getCell(1).getNumericCellValue());
                stuGradeInfo.setSubjectCredit((int)row.getCell(2).getNumericCellValue());
                stuGradeInfo.setCoreCredit((int) row.getCell(3).getNumericCellValue());
                stuGradeInfo.setDevelopmentCredit((int)row.getCell(4).getNumericCellValue());
                stuGradeInfo.setTotal((int)row.getCell(5).getNumericCellValue());
                stuGradeInfo.setGeneralScore(row.getCell(6).getNumericCellValue());
                stuGradeInfo.setSubjectScore(row.getCell(7).getNumericCellValue());
                stuGradeInfo.setCoreScore(row.getCell(8).getNumericCellValue());
                stuGradeInfo.setDevelopmentScore(row.getCell(9).getNumericCellValue());
                stuGradeInfo.setPracticeScore(row.getCell(10).getNumericCellValue());
                stuGradeInfo.setGpa(row.getCell(11).getNumericCellValue());
                stuGradeInfo.setCreatetime(new Date());
                stuGradeInfo.setUpdatetime(new Date());


                stuGradeInfoMapper.insert(stuGradeInfo);
            }
        }
    }

    private void importStuCourseData(Sheet sheet, int schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                StuCourseData stuCourseData = new StuCourseData();
                stuCourseData.setSchid(schId);
                stuCourseData.setStudentno(row.getCell(0).getStringCellValue());
                stuCourseData.setCourseno(row.getCell(1).getStringCellValue());
                stuCourseData.setCoursenm(row.getCell(2).getStringCellValue());
                stuCourseData.setSemester(row.getCell(3).getStringCellValue());
                stuCourseData.setCredits(row.getCell(4).getNumericCellValue());
                stuCourseData.setRegularGrade(row.getCell(5).getNumericCellValue());
                stuCourseData.setEndtermGrade(row.getCell(6).getNumericCellValue());
                stuCourseData.setScore(row.getCell(7).getNumericCellValue());
                stuCourseData.setGpa(row.getCell(8).getNumericCellValue());
                stuCourseData.setCreatetime(new Date());
                stuCourseData.setUpdatetime(new Date());


                stuCourseDataMapper.insert(stuCourseData);
            }
        }
    }
}
