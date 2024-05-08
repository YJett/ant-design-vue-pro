package com.example.llmauthentication.utils;

import com.example.llmauthentication.pojo.JbAbilityKnowledge;
import com.example.llmauthentication.pojo.KpKnowledgePoint;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static List<KpKnowledgePoint> readKnowledgePoints(MultipartFile file, Integer schId) throws IOException {
        List<KpKnowledgePoint> knowledgePoints = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  // 从第二行开始
                Row row = sheet.getRow(i);

                if (row != null) {
                    KpKnowledgePoint knowledgePoint = new KpKnowledgePoint();
                    knowledgePoint.setSchid(schId);
                    knowledgePoint.setKnowledgeid((int) row.getCell(0).getNumericCellValue());
                    knowledgePoint.setKnowledgenm(row.getCell(1).getStringCellValue());
                    knowledgePoint.setFlag((int) row.getCell(2).getNumericCellValue());
                    if (row.getCell(3) != null) {
                        knowledgePoint.setUplevel((int) row.getCell(3).getNumericCellValue());
                    }
                    knowledgePoint.setCreatetime(LocalDateTime.now());
                    knowledgePoint.setUpdatetime(LocalDateTime.now());

                    knowledgePoints.add(knowledgePoint);
                }
            }
        }

        return knowledgePoints;
    }

    public static List<JbAbilityKnowledge> readAbilityKnowledge(MultipartFile file, Integer schId) throws IOException {
        List<JbAbilityKnowledge> abilityKnowledgeList = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  // 从第二行开始
                Row row = sheet.getRow(i);

                if (row != null) {
                    Integer knowledgeId = (int) row.getCell(0).getNumericCellValue();

                    if (row.getCell(4) != null) {  // 处理能力项编号
                        String[] abilityIds = row.getCell(4).getStringCellValue().split(",");

                        for (String abilityIdStr : abilityIds) {
                            JbAbilityKnowledge abilityKnowledge = new JbAbilityKnowledge();
                            abilityKnowledge.setSchid(schId);
                            abilityKnowledge.setKnowledgeid(knowledgeId);
                            abilityKnowledge.setAbilityid(Integer.parseInt(abilityIdStr));
                            abilityKnowledge.setCreatetime(LocalDateTime.now());
                            abilityKnowledge.setUpdatetime(LocalDateTime.now());

                            abilityKnowledgeList.add(abilityKnowledge);
                        }
                    }
                }
            }
        }

        return abilityKnowledgeList;
    }
}
