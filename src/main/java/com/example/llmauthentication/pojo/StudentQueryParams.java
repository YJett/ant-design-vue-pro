package com.example.llmauthentication.pojo;

import lombok.Data;

@Data
public class StudentQueryParams {
    private Integer schid;
    private Integer jobid;
    private Integer abilityId;
    private Double score;
    private String scoreComparison;
    private String type;
    private Double minScore;
    private String minScoreComparison;
}
