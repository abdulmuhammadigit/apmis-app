package com.clean.persistence.processtracking;

import java.util.List;
import java.util.Optional;

import com.clean.domain.entity.prc.Stage;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface StageRepository extends JpaRepository<Stage,Integer> {
    boolean existsByNameAndStageTypeIdAndWorkFlowIdAndIdIsNot(String name,int stageTypeId,int workFlowId,int id);

    @Query("select S , T, W from Stage S join fetch StageType T on S.stageTypeId = T.id join fetch WorkFlow W on S.workFlowId = W.id")
    List<Stage> getStageDetailList();

    @Query("select S , T, W from Stage S join fetch StageType T on S.stageTypeId = T.id join fetch WorkFlow W on S.workFlowId = W.id where S.isActive=:isActive")
    List<Stage> getActiveStageDetailList(@Param("isActive") boolean isActive);

    Optional<Stage> findByWorkFlowIdAndStageTypeId(Integer workflowId,Integer stageTypeId);
}
