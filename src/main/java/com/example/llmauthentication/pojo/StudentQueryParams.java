package com.example.llmauthentication.pojo;

import lombok.Data;

import java.util.List;

@Data
public class StudentQueryParams {
    private Integer schid;
    private Integer jobid;
    private Integer abilityId;
    private Double score;
    private String scoreComparison;
    private List<String> types;
    private List<Double> minScores;
    private List<String> minScoreComparisons;
}
