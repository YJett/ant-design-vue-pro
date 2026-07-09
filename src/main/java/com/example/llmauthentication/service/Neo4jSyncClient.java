package com.example.llmauthentication.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class Neo4jSyncClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${neo4j.sync.enabled:true}")
    private boolean enabled;

    @Value("${neo4j.sync.base-url:http://localhost:8082}")
    private String baseUrl;

    public void syncFull(String reason) {
        if (!enabled) {
            return;
        }
        Map<String, Object> body = new HashMap<>();
        body.put("reason", reason);
        post("/sync/full", body, reason);
    }

    public void syncAbilityKnowledge(Integer schId, Integer abilityId, Integer knowledgeId, String reason) {
        if (!enabled) {
            return;
        }
        Map<String, Object> body = new HashMap<>();
        body.put("schId", schId);
        body.put("abilityId", abilityId);
        body.put("knowledgeId", knowledgeId);
        post("/sync/ability-knowledge", body, reason);
    }

    private void post(String path, Object body, String reason) {
        try {
            restTemplate.postForEntity(normalizedBaseUrl() + path, body, String.class);
        } catch (Exception ex) {
            log.warn("Neo4j sync trigger failed, reason={}", reason, ex);
        }
    }

    private String normalizedBaseUrl() {
        if (baseUrl.endsWith("/")) {
            return baseUrl.substring(0, baseUrl.length() - 1);
        }
        return baseUrl;
    }
}
