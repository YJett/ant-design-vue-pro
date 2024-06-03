package com.example.llmauthentication.mapper;

import com.example.llmauthentication.pojo.JbAbilityScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
* @author arthur
* @description 针对表【jb_ability_score】的数据库操作Mapper
* @createDate 2024-06-01 16:20:45
* @Entity com.example.llmauthentication.pojo.JbAbilityScore
*/
public interface JbAbilityScoreMapper extends BaseMapper<JbAbilityScore> {

    @Update("{call SF_INS_KNOWLEDGE(#{schId, mode=IN, jdbcType=INTEGER}, #{studentId, mode=IN, jdbcType=INTEGER})}")
    void callSfInsKnowledge(@Param("schId") Integer schId, @Param("studentId") Integer studentId);

    @Select("SELECT type, AVG(score) AS avg_score " +
            "FROM jb_ability_score " +
            "WHERE schid = #{schId} AND studentid = #{studentId} " +
            "GROUP BY type")
    List<Map<String, Object>> getAverageScoreByType(@Param("schId") Integer schId, @Param("studentId") Integer studentId);

    @Select("SELECT j.score, k.knowledgenm " +
            "FROM jb_ability_score j " +
            "JOIN kp_knowledge_point k ON j.knowledgeid = k.knowledgeid " +
            "WHERE j.schid = #{schId} " +
            "AND j.studentid = #{studentId} " +
            "AND j.type = #{type}")
    List<Map<String, Object>> getScoreAndKnowledgeName(@Param("schId") Integer schId,
                                                       @Param("studentId") Integer studentId,
                                                       @Param("type") String type);
}





