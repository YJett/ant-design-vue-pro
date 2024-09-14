package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.llmauthentication.mapper.*;
import com.example.llmauthentication.pojo.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * // TODO
 *
 * @author ranyouwei
 * @date 2024/5/20
 */

@Service
@Transactional
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
            queryWrapper.eq("schName", schoolName);
            SchInfo schInfo = schInfoMapper.selectOne(queryWrapper);
            Integer schId = schInfo.getSchId();
            QueryWrapper queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("schId", schId);
            // 导入学生基本信息
            studentInfoMapper.delete(queryWrapper1);
            Sheet studentBasicInfoSheet = workbook.getSheet("学生基本信息");
            if (studentBasicInfoSheet != null) {
                importStudentBasicInfo(studentBasicInfoSheet, schId, schoolName);
            }

            // 导入成绩总揽
            stuGradeInfoMapper.delete(queryWrapper1);
            Sheet stuGradeInfoSheet = workbook.getSheet("成绩总览");
            if (stuGradeInfoSheet != null) {
                importStuGradeInfo(stuGradeInfoSheet, schId);
            }

            // 导入课程详细信息
            stuCourseDataMapper.delete(queryWrapper1);
            Sheet stuCourseDataSheet = workbook.getSheet("课程详细成绩");
            if (stuCourseDataSheet != null) {
                importStuCourseData(stuCourseDataSheet, schId);
            }

            // 导入奖学金
            scholarshipInfoMapper.delete(queryWrapper1);
            Sheet scholarshipSheet = workbook.getSheet("奖学金");
            if (scholarshipSheet != null) {
                importScholarShip(scholarshipSheet, schId);
            }

            // 导入勤工助学记录
            workStudyInfoMapper.delete(queryWrapper1);
            Sheet workStudySheet = workbook.getSheet("勤工助学记录");
            if (workStudySheet != null) {
                importWorkStudyInfo(workStudySheet, schId);
            }

            // 导入宿舍卫生信息
            dormitoryHealthInfoMapper.delete(queryWrapper1);
            Sheet dormitoryHealthSheet = workbook.getSheet("宿舍卫生");
            if (dormitoryHealthSheet != null) {
                importDormitoryHealthInfo(dormitoryHealthSheet, schId);
            }

            // 导入绿色通道申请信息
            greenChannelsApplyMapper.delete(queryWrapper1);
            Sheet greenChannelsSheet = workbook.getSheet("绿色通道申请");
            if (greenChannelsSheet != null) {
                importGreenChannels(greenChannelsSheet, schId);
            }

            // 导入学生课程考勤情况
            stuAttendanceInfoMapper.delete(queryWrapper1);
            Sheet stuAttendanceSheet = workbook.getSheet("学生课程考勤情况");
            if (stuAttendanceSheet != null) {
                importStuAttendanceInfo(stuAttendanceSheet, schId);
            }

            // 导入实习情况
            internshipInfoMapper.delete(queryWrapper1);
            Sheet internshipSheet = workbook.getSheet("实习情况");
            if (internshipSheet != null) {
                importInternshipInfo(internshipSheet, schId);
            }

            // 导入技能证书情况
            certificateInfoMapper.delete(queryWrapper1);
            Sheet certificateSheet = workbook.getSheet("技能证书情况");
            if (certificateSheet != null) {
                importCertificateInfo(certificateSheet, schId);
            }

            // 导入竞赛获奖
            contestInfoMapper.delete(queryWrapper1);
            Sheet contestSheet = workbook.getSheet("竞赛获奖");
            if (contestSheet != null) {
                importContestInfo(contestSheet, schId);
            }

            // 导入参与社团情况
            associationInfoMapper.delete(queryWrapper1);
            Sheet associationSheet = workbook.getSheet("参与社团情况");
            if (associationSheet != null) {
                importAssociationIn(associationSheet, schId);
            }

            // 导入考勤情况
            attendanceInfoMapper.delete(queryWrapper1);
            Sheet attendanceSheet = workbook.getSheet("考勤情况");
            if (attendanceSheet != null) {
                importAttendanceInfo(attendanceSheet, schId);
            }
        }
    }

    private void importAttendanceInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                AttendanceInfo attendanceInfo = new AttendanceInfo();
                attendanceInfo.setSchid(schId);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    attendanceInfo.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    attendanceInfo.setTerm(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    attendanceInfo.setSubject(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
                    attendanceInfo.setAttendance((int) row.getCell(3).getNumericCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
                    attendanceInfo.setAbsenteeism((int) row.getCell(4).getNumericCellValue());
                }

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

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    associationInfo.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    associationInfo.setAssociationNm(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    associationInfo.setAssociationNo(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    associationInfo.setAssociationCd(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(row.getCell(4))) {
                    associationInfo.setStartDate(row.getCell(4).getDateCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(row.getCell(5))) {
                    associationInfo.setEndDate(row.getCell(5).getDateCellValue());
                }

                if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.STRING) {
                    associationInfo.setDuty(row.getCell(6).getStringCellValue());
                }

                if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.STRING) {
                    associationInfo.setParticipationLevel(row.getCell(7).getStringCellValue());
                }

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
                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    contestInfo.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    contestInfo.setContestnm(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    contestInfo.setLevel(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    contestInfo.setContestiid(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                    contestInfo.setContesttime(row.getCell(4).getStringCellValue());
                }

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
                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    certificateInfo.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    certificateInfo.setInstitution(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    certificateInfo.setLevel(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    certificateInfo.setCertiid(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                    certificateInfo.setCertinm(row.getCell(4).getStringCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.STRING){
                    certificateInfo.setIssueDate(row.getCell(5).getStringCellValue());
                }

                if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.STRING) {
                    certificateInfo.setElectronicFlg(row.getCell(6).getStringCellValue());
                }

                certificateInfo.setCreatetime(new Date());
                certificateInfo.setUpdatetime(new Date());
                certificateInfoMapper.insert(certificateInfo);
            }
        }
    }

    private void importInternshipInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                InternshipInfo internshipInfo = new InternshipInfo();
                internshipInfo.setSchid(schId);

                boolean hasData = false;

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    internshipInfo.setStudentno(row.getCell(0).getStringCellValue());
                    hasData = true;
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    internshipInfo.setIndustryCd(row.getCell(1).getStringCellValue());
                    hasData = true;
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    internshipInfo.setInternshipEnterprise(row.getCell(2).getStringCellValue());
                    hasData = true;
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    internshipInfo.setContent(row.getCell(3).getStringCellValue());
                    hasData = true;
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
                    internshipInfo.setDuratio((int) row.getCell(4).getNumericCellValue());
                    hasData = true;
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
                    internshipInfo.setScore((int) row.getCell(5).getNumericCellValue());
                    hasData = true;
                }

                if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.STRING) {
                    internshipInfo.setStartDate(row.getCell(6).getStringCellValue());
                    hasData = true;
                }

                if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.STRING) {
                    internshipInfo.setEndDate(row.getCell(7).getStringCellValue());
                    hasData = true;
                }

                if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.STRING) {
                    internshipInfo.setViolationFlg(row.getCell(8).getStringCellValue());
                    hasData = true;
                }

                if (row.getCell(9) != null && row.getCell(9).getCellType() == CellType.STRING) {
                    internshipInfo.setViolation(row.getCell(9).getStringCellValue());
                    hasData = true;
                }

                if (row.getCell(10) != null && row.getCell(10).getCellType() == CellType.STRING) {
                    internshipInfo.setJudgement(row.getCell(10).getStringCellValue());
                    hasData = true;
                }

                // 只有在至少有一个字段有值时，才会插入数据库
                if (hasData) {
                    internshipInfo.setCreatetime(new Date());
                    internshipInfo.setUpdatetime(new Date());

                    try {
                        internshipInfoMapper.insert(internshipInfo);
                    } catch (Exception e) {
                        // 记录日志或处理插入失败的情况
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void importStuAttendanceInfo(Sheet sheet, Integer schId) {
        List<StuAttendanceInfo> stuAttendanceInfoList = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
                stuAttendanceInfo.setSchid(schId);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    stuAttendanceInfo.setCourseno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    stuAttendanceInfo.setCoursenm(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    stuAttendanceInfo.setStudentname(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    stuAttendanceInfo.setStudentno(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                    stuAttendanceInfo.setDepartment(row.getCell(4).getStringCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.STRING) {
                    stuAttendanceInfo.setMajor(row.getCell(5).getStringCellValue());
                }

                if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.STRING) {
                    stuAttendanceInfo.setStudentclass(row.getCell(6).getStringCellValue());
                }

                if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setInEnthusiasm(row.getCell(7).getNumericCellValue());
                }

                if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setOutEnthusiasm(row.getCell(8).getNumericCellValue());
                }

                if (row.getCell(9) != null && row.getCell(9).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setScore(row.getCell(9).getNumericCellValue());
                }

                if (row.getCell(10) != null && row.getCell(10).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setRegularGrade(row.getCell(10).getNumericCellValue());
                }

                if (row.getCell(11) != null && row.getCell(11).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setPoints(row.getCell(11).getNumericCellValue());
                }

                if (row.getCell(12) != null && row.getCell(12).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setAttendanceRate(row.getCell(12).getNumericCellValue());
                }

                if (row.getCell(13) != null && row.getCell(13).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setAttendance((int) row.getCell(13).getNumericCellValue());
                }

                if (row.getCell(14) != null && row.getCell(14).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setReally((int) row.getCell(14).getNumericCellValue());
                }

                if (row.getCell(15) != null && row.getCell(15).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setAbsent((int) row.getCell(15).getNumericCellValue());
                }

                if (row.getCell(16) != null && row.getCell(16).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setLate((int) row.getCell(16).getNumericCellValue());
                }

                if (row.getCell(17) != null && row.getCell(17).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setEarly((int) row.getCell(17).getNumericCellValue());
                }

                if (row.getCell(18) != null && row.getCell(18).getCellType() == CellType.NUMERIC) {
                    stuAttendanceInfo.setLeaverequests((int) row.getCell(18).getNumericCellValue());
                }

                stuAttendanceInfo.setCreatetime(new Date());
                stuAttendanceInfo.setUpdatetime(new Date());

                stuAttendanceInfoList.add(stuAttendanceInfo);

                // 每1000条记录批量插入一次
                if (stuAttendanceInfoList.size() >= 1000) {
                    stuAttendanceInfoMapper.insertBatch(stuAttendanceInfoList);
                    stuAttendanceInfoList.clear();
                }
            }
        }

        // 插入剩余的记录
        if (!stuAttendanceInfoList.isEmpty()) {
            stuAttendanceInfoMapper.insertBatch(stuAttendanceInfoList);
        }
    }

    private void importGreenChannels(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                GreenChannelsApply greenChannelsApply = new GreenChannelsApply();
                greenChannelsApply.setSchid(schId);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    greenChannelsApply.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.NUMERIC) {
                    greenChannelsApply.setAmt(row.getCell(1).getNumericCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    greenChannelsApply.setDelayclass(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    greenChannelsApply.setApply(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                    greenChannelsApply.setIncomeType(row.getCell(4).getStringCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
                    greenChannelsApply.setIncome(row.getCell(5).getNumericCellValue());
                }

                greenChannelsApply.setCreatetime(new Date());
                greenChannelsApply.setUpdatetime(new Date());

                try {
                    greenChannelsApplyMapper.insert(greenChannelsApply);
                } catch (Exception e) {
                    // 记录日志或处理插入失败的情况
                    e.printStackTrace();
                }
            }
        }
    }

    private void importDormitoryHealthInfo(Sheet sheet, Integer schId) {
        List<DormitoryHealthInfo> dormitoryHealthInfoList = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                DormitoryHealthInfo dormitoryHealthInfo = new DormitoryHealthInfo();
                dormitoryHealthInfo.setSchid(schId);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    dormitoryHealthInfo.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    dormitoryHealthInfo.setStudentnm(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    dormitoryHealthInfo.setDomitoryNo(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    dormitoryHealthInfo.setRoomNo(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                    dormitoryHealthInfo.setGrade(row.getCell(4).getStringCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.STRING) {
                    dormitoryHealthInfo.setStudentclass(row.getCell(5).getStringCellValue());
                }

                if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.NUMERIC) {
                    dormitoryHealthInfo.setCheckDate(row.getCell(6).getDateCellValue());
                }

                if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.STRING) {
                    dormitoryHealthInfo.setCheckType(row.getCell(7).getStringCellValue());
                }

                if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.NUMERIC) {
                    dormitoryHealthInfo.setWeek((int) row.getCell(8).getNumericCellValue());
                }

                if (row.getCell(9) != null && row.getCell(9).getCellType() == CellType.NUMERIC) {
                    dormitoryHealthInfo.setScore((int) row.getCell(9).getNumericCellValue());
                }

                dormitoryHealthInfo.setCreatetime(new Date());
                dormitoryHealthInfo.setUpdatetime(new Date());

                dormitoryHealthInfoList.add(dormitoryHealthInfo);

                // 每1000条记录批量插入一次
                if (dormitoryHealthInfoList.size() >= 1000) {
                    dormitoryHealthInfoMapper.insertBatch(dormitoryHealthInfoList);
                    dormitoryHealthInfoList.clear();  // 清空列表
                }
            }
        }

        // 插入剩余的记录
        if (!dormitoryHealthInfoList.isEmpty()) {
            dormitoryHealthInfoMapper.insertBatch(dormitoryHealthInfoList);
        }
    }

    private void importWorkStudyInfo(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                WorkStudyInfo workStudyInfo = new WorkStudyInfo();
                workStudyInfo.setSchid(schId);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    workStudyInfo.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    workStudyInfo.setName(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.NUMERIC) {
                    workStudyInfo.setPrizes(row.getCell(2).getNumericCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    workStudyInfo.setUnit(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
                    workStudyInfo.setStartDate(row.getCell(4).getDateCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
                    workStudyInfo.setEndDate(row.getCell(5).getDateCellValue());
                }

                workStudyInfo.setCreatetime(new Date());
                workStudyInfo.setUpdatetime(new Date());

                try {
                    workStudyInfoMapper.insert(workStudyInfo);
                } catch (Exception e) {
                    // 记录日志或处理插入失败的情况
                    e.printStackTrace();
                }
            }
        }
    }

    private void importScholarShip(Sheet sheet, Integer schId) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                ScholarshipInfo scholarshipInfo = new ScholarshipInfo();
                scholarshipInfo.setSchid(schId);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    scholarshipInfo.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    scholarshipInfo.setName(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    scholarshipInfo.setScholarshipclass(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    scholarshipInfo.setLevel(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
                    scholarshipInfo.setPrizes(row.getCell(4).getNumericCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.STRING) {
                    scholarshipInfo.setYyyymm(row.getCell(5).getStringCellValue());
                }

                scholarshipInfo.setCreatetime(new Date());
                scholarshipInfo.setUpdatetime(new Date());

                try {
                    scholarshipInfoMapper.insert(scholarshipInfo);
                } catch (Exception e) {
                    // 记录日志或处理插入失败的情况
                    e.printStackTrace();
                }
            }
        }
    }

    private void importStudentBasicInfo(Sheet sheet, int schId, String schNmane) {
        List<StudentInfo> studentInfoList = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setSchid(schId);
                studentInfo.setSchnm(schNmane);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    studentInfo.setDepartment(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    studentInfo.setMajor(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    studentInfo.setStudentclass(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    studentInfo.setStudentno(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                    studentInfo.setStudentnm(row.getCell(4).getStringCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.STRING) {
                    studentInfo.setGender(row.getCell(5).getStringCellValue());
                }

                if (row.getCell(6) != null) {
                    if (row.getCell(6).getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(row.getCell(6))) {
                        studentInfo.setBirthday(row.getCell(6).getDateCellValue());
                    }
                }

                if (row.getCell(7) != null) {
                    if (row.getCell(7).getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(row.getCell(7))) {
                        studentInfo.setEnrollmentDate(row.getCell(7).getDateCellValue());
                    }
                }

                if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.STRING) {
                    studentInfo.setParty(row.getCell(8).getStringCellValue());
                }

                if (row.getCell(9) != null && row.getCell(9).getCellType() == CellType.STRING) {
                    studentInfo.setHometown(row.getCell(9).getStringCellValue());
                }

                if (row.getCell(10) != null && row.getCell(10).getCellType() == CellType.STRING) {
                    studentInfo.setHealth(row.getCell(10).getStringCellValue());
                }

                if (row.getCell(11) != null && row.getCell(11).getCellType() == CellType.STRING) {
                    studentInfo.setDisability(row.getCell(11).getStringCellValue());
                }

                studentInfo.setCreatetime(new Date());
                studentInfo.setUpdatetime(new Date());

                // 添加到批量插入列表
                studentInfoList.add(studentInfo);

                // 批量插入，每1000条一次
                if (studentInfoList.size() >= 1000) {
                    studentInfoMapper.insertBatch(studentInfoList);
                    studentInfoList.clear(); // 清空列表，准备下一个批次
                }
            }
        }

        // 插入剩余的记录
        if (!studentInfoList.isEmpty()) {
            studentInfoMapper.insertBatch(studentInfoList);
        }
    }

    private void importStuGradeInfo(Sheet sheet, int schId) {
        List<StuGradeInfo> stuGradeInfoList = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                StuGradeInfo stuGradeInfo = new StuGradeInfo();
                stuGradeInfo.setSchid(schId);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    stuGradeInfo.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setGeneralCredit((int) row.getCell(1).getNumericCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setSubjectCredit((int) row.getCell(2).getNumericCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setCoreCredit((int) row.getCell(3).getNumericCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setDevelopmentCredit((int) row.getCell(4).getNumericCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setTotal((int) row.getCell(5).getNumericCellValue());
                }

                if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setGeneralScore(row.getCell(6).getNumericCellValue());
                }

                if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setSubjectScore(row.getCell(7).getNumericCellValue());
                }

                if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setCoreScore(row.getCell(8).getNumericCellValue());
                }

                if (row.getCell(9) != null && row.getCell(9).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setDevelopmentScore(row.getCell(9).getNumericCellValue());
                }

                if (row.getCell(10) != null && row.getCell(10).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setPracticeScore(row.getCell(10).getNumericCellValue());
                }

                if (row.getCell(11) != null && row.getCell(11).getCellType() == CellType.NUMERIC) {
                    stuGradeInfo.setGpa(row.getCell(11).getNumericCellValue());
                }

                stuGradeInfo.setCreatetime(new Date());
                stuGradeInfo.setUpdatetime(new Date());

                // 添加到批量插入列表
                stuGradeInfoList.add(stuGradeInfo);

                // 每1000条记录插入一次
                if (stuGradeInfoList.size() >= 1000) {
                    stuGradeInfoMapper.insertBatch(stuGradeInfoList);
                    stuGradeInfoList.clear(); // 清空列表，准备下一个批次
                }
            }
        }

        // 插入剩余的记录
        if (!stuGradeInfoList.isEmpty()) {
            stuGradeInfoMapper.insertBatch(stuGradeInfoList);
        }
    }

    private void importStuCourseData(Sheet sheet, int schId) {
        List<StuCourseData> stuCourseDataList = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                StuCourseData stuCourseData = new StuCourseData();
                stuCourseData.setSchid(schId);

                if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                    stuCourseData.setStudentno(row.getCell(0).getStringCellValue());
                }

                if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                    stuCourseData.setCourseno(row.getCell(1).getStringCellValue());
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                    stuCourseData.setCoursenm(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                    stuCourseData.setSemester(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
                    stuCourseData.setCredits(row.getCell(4).getNumericCellValue());
                }

                if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
                    stuCourseData.setRegularGrade(row.getCell(5).getNumericCellValue());
                }

                if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.NUMERIC) {
                    stuCourseData.setEndtermGrade(row.getCell(6).getNumericCellValue());
                }

                if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.NUMERIC) {
                    stuCourseData.setScore(row.getCell(7).getNumericCellValue());
                }

                if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.NUMERIC) {
                    stuCourseData.setGpa(row.getCell(8).getNumericCellValue());
                }

                stuCourseData.setCreatetime(new Date());
                stuCourseData.setUpdatetime(new Date());

                stuCourseDataList.add(stuCourseData);

                // 每1000条进行一次批量插入
                if (stuCourseDataList.size() >= 1000) {
                    stuCourseDataMapper.insertBatch(stuCourseDataList);
                    stuCourseDataList.clear();  // 清空列表
                }
            }
        }

        // 插入剩余的记录
        if (!stuCourseDataList.isEmpty()) {
            stuCourseDataMapper.insertBatch(stuCourseDataList);
        }
    }

}