package com.example.llmauthentication.controller;

import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.JbAbilityKnowledge;
import com.example.llmauthentication.service.impl.JbAbilityKnowledgeServiceImpl;
import com.example.llmauthentication.service.impl.abilityDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/jbAbilityKnowledge")
public class JbAbilityKnowledgeController {

    @Autowired
    private JbAbilityKnowledgeServiceImpl jbAbilityKnowledgeService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        JbAbilityKnowledge jbAbilityKnowledge = jbAbilityKnowledgeService.getById(id);
        return Result.success(jbAbilityKnowledge);
    }

    @PostMapping("/insert")
    public Result save(@RequestBody JbAbilityKnowledge jbAbilityKnowledge) {
        jbAbilityKnowledge.setCreatetime(LocalDateTime.now());
        jbAbilityKnowledge.setUpdatetime(LocalDateTime.now());
        jbAbilityKnowledgeService.save(jbAbilityKnowledge);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(
            @RequestParam Integer schid,
            @RequestParam Integer abilityid,
            @RequestParam Integer knowledgeid,
            @RequestParam(required = false) LocalDateTime createtime,
            @RequestParam(required = false) LocalDateTime updatetime) {
        JbAbilityKnowledge jbAbilityKnowledge = new JbAbilityKnowledge();
        jbAbilityKnowledge.setSchid(schid);
        jbAbilityKnowledge.setAbilityid(abilityid);
        jbAbilityKnowledge.setKnowledgeid(knowledgeid);
        jbAbilityKnowledge.setCreatetime(createtime != null ? createtime : LocalDateTime.now());
        jbAbilityKnowledge.setUpdatetime(updatetime != null ? updatetime : LocalDateTime.now());
        jbAbilityKnowledgeService.updateById(jbAbilityKnowledge);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Integer id) {
        jbAbilityKnowledgeService.removeById(id);
        return Result.success();
    }
}
