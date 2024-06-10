package com.example.llmauthentication.utils;

import com.example.llmauthentication.pojo.StudentQueryParams;
import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

public class StudentInfoProvider {
    public String buildStudentInfoQuery(Map<String, Object> params) {
        StudentQueryParams queryParams = (StudentQueryParams) params.get("params");
        return new SQL() {{
            SELECT("i.studentNm, sc.schnm as schName, i.hometown, i.party, a.info as scholarship, " +
                   "b.info as contest, c.info as certificate, g.gpa");
            FROM("student_info i");
            INNER_JOIN("school_info sc ON sc.schid = i.schid");
            LEFT_OUTER_JOIN("stu_grade_info g ON g.studentno = i.studentno AND g.schid = i.schid");
            LEFT_OUTER_JOIN("(SELECT schid, studentno, group_concat(yyyymm, name, level separator ';') as info " +
                            "FROM scholarship_info s GROUP BY schid, studentno) a ON a.studentno = i.studentno AND a.schid = i.schid");
            LEFT_OUTER_JOIN("(SELECT schid, studentno, group_concat(contestNm, contestiId separator ';') as info " +
                            "FROM contest_info s GROUP BY schid, studentno) b ON b.studentno = i.studentno AND b.schid = i.schid");
            LEFT_OUTER_JOIN("(SELECT schid, studentno, group_concat(certiNm separator ';') as info " +
                            "FROM certificate_info s GROUP BY schid, studentno) c ON c.studentno = i.studentno AND c.schid = i.schid");
            WHERE("1=1");
            if (queryParams.getSchid() != null) {
                WHERE("i.schid = #{params.schid}");
            }
            if (queryParams.getJobid() != null && queryParams.getAbilityId() != null) {
                StringBuilder joinCondition = new StringBuilder();
                joinCondition.append("stu_ability_score e ON e.schid = i.schid AND e.studentid = i.studentno ");
                joinCondition.append("AND e.jobid = #{params.jobid} AND e.abilityid = #{params.abilityId}");
                if (queryParams.getScore() != null && queryParams.getScoreComparison() != null) {
                    joinCondition.append(" AND e.score ")
                                 .append(queryParams.getScoreComparison())
                                 .append(" #{params.score}");
                }
                INNER_JOIN(joinCondition.toString());
            }
            if (queryParams.getType() != null) {
                StringBuilder joinCondition = new StringBuilder();
                joinCondition.append("(SELECT studentid, type, AVG(score) as score FROM jb_ability_score ");
                joinCondition.append("WHERE schid = #{params.schid} GROUP BY studentid, type) f ");
                joinCondition.append("ON f.studentid = i.studentno AND f.type = #{params.type}");
                if (queryParams.getMinScore() != null && queryParams.getMinScoreComparison() != null) {
                    joinCondition.append(" AND f.score ")
                                 .append(queryParams.getMinScoreComparison())
                                 .append(" #{params.minScore}");
                }
                INNER_JOIN(joinCondition.toString());
            }
        }}.toString();
    }
}
