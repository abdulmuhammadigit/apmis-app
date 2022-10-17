package com.clean.persistence.processtracking;

import java.util.Optional;

import com.clean.domain.entity.prc.WorkFlow;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface WorkFlowRepository extends JpaRepository<WorkFlow,Integer> {
    boolean existsByNameAndIdIsNot(String name,int id);
    WorkFlow findByEntityId(int entityId);
    Optional<WorkFlow> findByEntityIdAndWorkFlowId(int entityId,int workFlowId);
    Optional<WorkFlow> findByEntityIdAndClassificationId(Integer entityId,Integer classificationId);
}
