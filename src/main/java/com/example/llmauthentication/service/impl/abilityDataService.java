package com.example.llmauthentication.service.impl;

import com.example.llmauthentication.mapper.JbJobMapper;
import com.example.llmauthentication.mapper.JobAbilityMapper;
import com.example.llmauthentication.mapper.JobJobAbilityMapper;
import com.example.llmauthentication.pojo.JbJob;
import com.example.llmauthentication.pojo.JobAbility;
import com.example.llmauthentication.pojo.JobJobAbility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class abilityDataService {
    @Autowired
    private JbJobMapper jobMapper;

    @Autowired
    private JobJobAbilityMapper jobAbilityMapper;

    @Autowired
    private JobAbilityMapper abilityMapper;

    public void importData(MultipartFile file) throws IOException {
        List<Object> data = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            // 导入岗位表数据
            JbJob job = new JbJob();
            job.setJobname(sheet.getRow(0).getCell(1).getStringCellValue());
            job.setJobdesp(sheet.getRow(1).getCell(1).getStringCellValue());
            job.setCreatetime(LocalDateTime.now());
            job.setUpdatetime(LocalDateTime.now());
            jobMapper.insert(job);

            Integer jobId = job.getJobid();

            // 导入岗位能力表数据
            JobJobAbility jobAbility = new JobJobAbility();
            jobAbility.setJobid(jobId);
            jobAbility.setAbilityid((int) sheet.getRow(3).getCell(0).getNumericCellValue());
            jobAbility.setCreatetime(LocalDateTime.now());
            jobAbility.setUpdatetime(LocalDateTime.now());
            jobAbilityMapper.insert(jobAbility);

            Integer abilityId =(int) sheet.getRow(3).getCell(0).getNumericCellValue();
            // 导入能力表数据
            for (int i = 4; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                JobAbility ability = new JobAbility();

                ability.setAbilityid(abilityId);
                ability.setAbilityno(row.getCell(0).getStringCellValue());
                ability.setAbilitynm(row.getCell(1).getStringCellValue());
                ability.setLevel((int) row.getCell(2).getNumericCellValue());
                if (row.getCell(3) != null) {
                    ability.setUpabilityid((int) row.getCell(3).getNumericCellValue());
                }
                ability.setCreatetime(LocalDateTime.now());
                ability.setUpdatetime(LocalDateTime.now());
                abilityMapper.insert(ability);
            }
        }
    }
}
