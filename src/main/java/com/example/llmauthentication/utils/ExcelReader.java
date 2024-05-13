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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static List<KpKnowledgePoint> readKnowledgePoints(MultipartFile file, Integer schId) throws IOException {
        List<KpKnowledgePoint> knowledgePoints = new ArrayList<>();

        Map<String, Integer> flagMapping = new HashMap<>();
        flagMapping.put("一级", 1);
        flagMapping.put("二级", 2);
        flagMapping.put("三级", 3);

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  // 从第二行开始
                Row row = sheet.getRow(i);

                if (row != null) {
                    KpKnowledgePoint knowledgePoint = new KpKnowledgePoint();
                    knowledgePoint.setSchid(schId);

                    // 确保所有单元格存在并且正确类型
                    if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.NUMERIC) {
                        knowledgePoint.setKnowledgeid((int) row.getCell(0).getNumericCellValue());
                    }

                    if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                        knowledgePoint.setKnowledgenm(row.getCell(1).getStringCellValue());
                    }

                    if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                        String flagValue = row.getCell(2).getStringCellValue();
                        knowledgePoint.setFlag(flagMapping.getOrDefault(flagValue, 0)); // 默认值0
                    }

                    if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
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
                    // 确保 knowledgeId 有效
                    if (row.getCell(0) == null || row.getCell(0).getCellType() != CellType.NUMERIC) {
                        continue;  // 如果第一个单元格为空或不是数值，则跳过该行
                    }
                    Integer knowledgeId = (int) row.getCell(0).getNumericCellValue();

                    // 处理能力项编号
                    if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
                        String[] abilityIds = row.getCell(4).getStringCellValue().split(",");
                        for (String abilityIdStr : abilityIds) {
                            if (abilityIdStr.trim().isEmpty()) {
                                continue;  // 跳过空字符串
                            }

                            JbAbilityKnowledge abilityKnowledge = new JbAbilityKnowledge();
                            abilityKnowledge.setSchid(schId);
                            abilityKnowledge.setKnowledgeid(knowledgeId);
                            abilityKnowledge.setAbilityid(Integer.parseInt(abilityIdStr.trim()));  // 确保无空格
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