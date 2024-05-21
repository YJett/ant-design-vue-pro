package com.example.llmauthentication.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.llmauthentication.mapper.CourseInfoMapper;
import com.example.llmauthentication.pojo.CourseInfo;
import com.example.llmauthentication.service.CourseInfoService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author arthur
* @description 针对表【course_info】的数据库操作Service实现
* @createDate 2024-05-16 10:27:17
*/
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo>
    implements CourseInfoService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public void importCourseData(MultipartFile file, Integer schId) throws IOException {
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            // 导入课程信息
            importCourseInfo(workbook.getSheetAt(0), schId);
            //todo 单元信息
        }
    }



    private void importCourseInfo(Sheet sheet, Integer schid) {

        Map<Integer,String> flagMapping = new HashMap<>();
        flagMapping.put(1, "理论课");
        flagMapping.put(2, "实践课");
        flagMapping.put(3, "综合课");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                CourseInfo courseInfo = new CourseInfo();
                courseInfo.setSchid(schid);
                if (row.getCell(0) != null) {
                    courseInfo.setCourseno(row.getCell(0).getStringCellValue());
                }
                if (row.getCell(1) != null) {
                    courseInfo.setCoursenm(row.getCell(1).getStringCellValue());
                }
                if (row.getCell(2) != null) {
                    courseInfo.setClasshour((int) row.getCell(2).getNumericCellValue());
                }
                if (row.getCell(3) != null) {
                    courseInfo.setCredit((int) row.getCell(3).getNumericCellValue());
                }
                if (row.getCell(4) != null) {
                    courseInfo.setTeacher(row.getCell(4).getStringCellValue());
                }
                if (row.getCell(5) != null) {
                    courseInfo.setUnits((int) row.getCell(5).getNumericCellValue());
                }
                if (row.getCell(6) != null) {
                    Integer flag = (int) row.getCell(6).getNumericCellValue();
                    courseInfo.setFlg(flagMapping.getOrDefault(flag,"理论课"));
                }
                if (row.getCell(7) != null) {
                    courseInfo.setKnowledgeid((int) row.getCell(7).getNumericCellValue());
                }
                if (row.getCell(8) != null) {
                    courseInfo.setType(row.getCell(8).getStringCellValue());
                }
                courseInfo.setCreatetime(new Date());
                courseInfo.setUpdatetime(new Date());
                courseInfoMapper.insert(courseInfo);

            }
        }
    }
}




