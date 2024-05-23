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

    @Autowired
    private DormitoryHealthInfoMapper dormitoryHealthInfoMapper;

    @Autowired
    private GreenChannelsApplyMapper greenChannelsApplyMapper;

    @Autowired
    private StuAttendanceInfoMapper stuAttendanceInfoMapper;

    @Autowired
    private InternshipInfoMapper internshipInfoMapper;

    @Autowired
    private CertificateInfoMapper certificateInfoMapper;

    @Autowired
    private ContestInfoMapper contestInfoMapper;

    @Autowired
    private AssociationInfoMapper associationInfoMapper;

    @Autowired
    private AttendanceInfoMapper attendanceInfoMapper;

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
            //导入宿舍卫生信息
            //importDormitoryHealthInfo(workbook.getSheet("宿舍卫生"),schId);
            //导入绿色通道申请信息
            //importGreenChannels(workbook.getSheet("绿色通道申请"),schId);
            //导入学生课程考勤情况
            //importStuAttendanceInfo(workbook.getSheet("学生课程考勤情况"),schId);
            //导入实习情况
            //importIntershipInfo(workbook.getSheet("实习情况"),schId);
            //导入技能证书情况
            //importCertificateInfo(workbook.getSheet("技能证书情况"),schId);
            //导入竞赛获奖
            importContestInfo(workbook.getSheet("技能证书情况"),schId);
            //导入参与社团情况
            importAssociationIn(workbook.getSheet("参与社团情况"),schId);
            //导入考勤情况
            importAttendanceInfo(workbook.getSheet("考勤情况"),schId);
        }
    }

    private void importAttendanceInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                AttendanceInfo attendanceInfo = new AttendanceInfo();
                attendanceInfo.setSchid(schId);
                attendanceInfo.setStudentno(row.getCell(0).getStringCellValue());
                attendanceInfo.setTerm(row.getCell(1).getStringCellValue());
                attendanceInfo.setSubject(row.getCell(2).getStringCellValue());
                attendanceInfo.setAttendance((int)row.getCell(3).getNumericCellValue());
                attendanceInfo.setAbsenteeism((int)row.getCell(4).getNumericCellValue());
                attendanceInfo.setCreatetime(new Date());
                attendanceInfo.setUpdatetime(new Date());
                attendanceInfoMapper.insert(attendanceInfo);
            }
        }
    }

    private void importAssociationIn(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                AssociationInfo associationInfo = new AssociationInfo();
                associationInfo.setSchid(schId);
                associationInfo.setStudentno(row.getCell(0).getStringCellValue());
                associationInfo.setAssociationNm(row.getCell(1).getStringCellValue());
                associationInfo.setAssociationNo(row.getCell(2).getStringCellValue());
                associationInfo.setAssociationCd( row.getCell(3).getStringCellValue());
                associationInfo.setStartDate(row.getCell(4).getDateCellValue());
                associationInfo.setEndDate(row.getCell(5).getDateCellValue());
                associationInfo.setDuty(row.getCell(6).getStringCellValue());
                associationInfo.setParticipationLevel(row.getCell(7).getStringCellValue());
                associationInfo.setCreatetime(new Date());
                associationInfo.setUpdatetime(new Date());
                associationInfoMapper.insert(associationInfo);
            }
        }
    }

    private void importContestInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                ContestInfo contestInfo = new ContestInfo();
                contestInfo.setSchid(schId);
                contestInfo.setStudentno(row.getCell(0).getStringCellValue());
                contestInfo.setContestnm(row.getCell(1).getStringCellValue());
                contestInfo.setLevel(row.getCell(2).getStringCellValue());
                contestInfo.setContestiid( row.getCell(3).getStringCellValue());
                contestInfo.setContesttime(row.getCell(4).getStringCellValue());
                contestInfo.setCreatetime(new Date());
                contestInfo.setUpdatetime(new Date());
                contestInfoMapper.insert(contestInfo);
            }
        }
    }

    private void importCertificateInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                CertificateInfo certificateInfo = new CertificateInfo();
                certificateInfo.setSchid(schId);
                certificateInfo.setStudentno(row.getCell(0).getStringCellValue());
                certificateInfo.setInstitution(row.getCell(1).getStringCellValue());
                certificateInfo.setLevel(row.getCell(2).getStringCellValue());
                certificateInfo.setCertiid( row.getCell(3).getStringCellValue());
                certificateInfo.setCertinm(row.getCell(4).getStringCellValue());
                certificateInfo.setIssueDate(row.getCell(5).getStringCellValue());
                certificateInfo.setElectronicFlg(row.getCell(6).getStringCellValue());
                certificateInfo.setCreatetime(new Date());
                certificateInfo.setUpdatetime(new Date());
                certificateInfoMapper.insert(certificateInfo);
            }
        }
    }

    private void importIntershipInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                InternshipInfo internshipInfo = new InternshipInfo();
                internshipInfo.setSchid(schId);
                internshipInfo.setStudentno(row.getCell(0).getStringCellValue());
                internshipInfo.setIndustryCd(row.getCell(1).getStringCellValue());
                internshipInfo.setInternshipEnterprise(row.getCell(2).getStringCellValue());
                internshipInfo.setContent( row.getCell(3).getStringCellValue());
                internshipInfo.setDuratio((int) row.getCell(4).getNumericCellValue());
                internshipInfo.setScore((int) row.getCell(5).getNumericCellValue());
                internshipInfo.setStartDate(row.getCell(6).getStringCellValue());
                internshipInfo.setEndDate(row.getCell(7).getStringCellValue());
                internshipInfo.setCreatetime(new Date());
                internshipInfo.setUpdatetime(new Date());
                internshipInfoMapper.insert(internshipInfo);
            }
        }
    }

    private void importStuAttendanceInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
                stuAttendanceInfo.setSchid(schId);
                stuAttendanceInfo.setCourseno(row.getCell(0).getStringCellValue());
                stuAttendanceInfo.setCoursenm(row.getCell(1).getStringCellValue());
                stuAttendanceInfo.setStudentname(row.getCell(2).getStringCellValue());
                stuAttendanceInfo.setStudentno( row.getCell(3).getStringCellValue());
                stuAttendanceInfo.setDepartment( row.getCell(4).getStringCellValue());
                stuAttendanceInfo.setMajor( row.getCell(5).getStringCellValue());
                stuAttendanceInfo.setStudentclass( row.getCell(6).getStringCellValue());
                stuAttendanceInfo.setInEnthusiasm(row.getCell(7).getNumericCellValue());
                stuAttendanceInfo.setOutEnthusiasm(row.getCell(8).getNumericCellValue());
                stuAttendanceInfo.setScore(row.getCell(9).getNumericCellValue());
                stuAttendanceInfo.setRegularGrade(row.getCell(10).getNumericCellValue());
                stuAttendanceInfo.setPoints(row.getCell(11).getNumericCellValue());
                stuAttendanceInfo.setAttendanceRate(row.getCell(12).getNumericCellValue());
                stuAttendanceInfo.setAttendance((int) row.getCell(13).getNumericCellValue());
                stuAttendanceInfo.setReally((int) row.getCell(14).getNumericCellValue());
                stuAttendanceInfo.setAbsent((int) row.getCell(15).getNumericCellValue());
                stuAttendanceInfo.setLate((int) row.getCell(16).getNumericCellValue());
                stuAttendanceInfo.setEarly((int) row.getCell(17).getNumericCellValue());
                stuAttendanceInfo.setLeave((int) row.getCell(18).getNumericCellValue());
                stuAttendanceInfo.setCreatetime(new Date());
                stuAttendanceInfo.setUpdatetime(new Date());
                stuAttendanceInfoMapper.insert(stuAttendanceInfo);
            }
        }
    }

    private void importGreenChannels(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                GreenChannelsApply greenChannelsApply = new GreenChannelsApply();
                greenChannelsApply.setSchid(schId);
                greenChannelsApply.setStudentno(row.getCell(0).getStringCellValue());
                greenChannelsApply.setAmt(row.getCell(1).getNumericCellValue());
                greenChannelsApply.setDelayclass(row.getCell(2).getStringCellValue());
                greenChannelsApply.setApply( row.getCell(3).getStringCellValue());
                greenChannelsApply.setIncomeType( row.getCell(4).getStringCellValue());
                greenChannelsApply.setIncome( row.getCell(5).getNumericCellValue());
                greenChannelsApply.setCreatetime(new Date());
                greenChannelsApply.setUpdatetime(new Date());
                greenChannelsApplyMapper.insert(greenChannelsApply);
            }
        }

    }

    private void importDormitoryHealthInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                DormitoryHealthInfo dormitoryHealthInfo = new DormitoryHealthInfo();
                dormitoryHealthInfo.setSchid(schId);
                dormitoryHealthInfo.setStudentno(row.getCell(0).getStringCellValue());
                dormitoryHealthInfo.setRoomNo(row.getCell(1).getStringCellValue());
                dormitoryHealthInfo.setLevel(row.getCell(2).getStringCellValue());
                dormitoryHealthInfo.setCheckDate( row.getCell(3).getDateCellValue());
                dormitoryHealthInfo.setCreatetime(new Date());
                dormitoryHealthInfo.setUpdatetime(new Date());
                dormitoryHealthInfoMapper.insert(dormitoryHealthInfo);
            }
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
