package com.example.llmauthentication.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.llmauthentication.common.result.Result;
import com.example.llmauthentication.pojo.JbAbilityKnowledge;
import com.example.llmauthentication.service.Neo4jSyncClient;
import com.example.llmauthentication.service.impl.JbAbilityKnowledgeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/jbAbilityKnowledge")
public class JbAbilityKnowledgeController {

    @Autowired
    private JbAbilityKnowledgeServiceImpl jbAbilityKnowledgeService;
    @Autowired
    private Neo4jSyncClient neo4jSyncClient;

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        JbAbilityKnowledge jbAbilityKnowledge = jbAbilityKnowledgeService.getById(id);
        return Result.success(jbAbilityKnowledge);
    }

    @PostMapping("/insert")
    public Result save(@RequestBody JbAbilityKnowledge jbAbilityKnowledge) {
        jbAbilityKnowledge.setCreatetime(LocalDateTime.now());
        jbAbilityKnowledge.setUpdatetime(LocalDateTime.now());
        boolean saved = jbAbilityKnowledgeService.save(jbAbilityKnowledge);
        if (saved) {
            neo4jSyncClient.syncAbilityKnowledge(
                    jbAbilityKnowledge.getSchid(),
                    jbAbilityKnowledge.getAbilityid(),
                    jbAbilityKnowledge.getKnowledgeid(),
                    "ability-knowledge-insert");
        }
        return Result.judge(saved);
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
        QueryWrapper<JbAbilityKnowledge> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("schId", schid)
                .eq("abilityId", abilityid)
                .eq("knowledgeId", knowledgeid);
        boolean updated = jbAbilityKnowledgeService.update(jbAbilityKnowledge, queryWrapper);
        if (updated) {
            neo4jSyncClient.syncAbilityKnowledge(schid, abilityid, knowledgeid, "ability-knowledge-update");
        }
        return Result.judge(updated);
    }

    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Integer id) {
        boolean removed = jbAbilityKnowledgeService.removeById(id);
        if (removed) {
            neo4jSyncClient.syncFull("ability-knowledge-delete");
        }
        return Result.judge(removed);
    }
}
