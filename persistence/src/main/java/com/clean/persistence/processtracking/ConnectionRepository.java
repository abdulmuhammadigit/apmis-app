package com.clean.persistence.processtracking;

import java.util.List;

import com.clean.domain.entity.prc.Connection;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ConnectionRepository extends JpaRepository<Connection,Integer> {
    boolean existsByStageIdAndToStageIdAndIdIsNot(int stageId,int toStageId,int id);
    
    @Query("select C,S,B from Connection C join fetch Stage S on C.toStageId = S.id join fetch BusinessRule B on C.businessRuleId = B.id join fetch UserStage US on US.stageId = S.id where C.stageId =:stageId and US.userId =:userId")
    List<Connection> findAllByStageIdAndUserId(@Param("stageId") int stageId,@Param("userId") Long userId);

    @Query("select C,S,B from Connection C join fetch Stage S on C.toStageId = S.id join fetch BusinessRule B on C.businessRuleId = B.id where C.stageId =:stageId")
    List<Connection> findAllByStageId(@Param("stageId") int stageId);

    @Query("select C,B from Connection C join fetch BusinessRule B on C.businessRuleId = B.id where C.stageId =:stageId and C.toStageId =:toStageId")
    Connection findByStageAndToStage(@Param("stageId") int stageId , @Param("toStageId") int toStageId);
}
