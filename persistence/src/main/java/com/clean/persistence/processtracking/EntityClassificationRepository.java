package com.clean.persistence.processtracking;

import java.util.List;

import com.clean.domain.entity.prc.EntityClassification;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface EntityClassificationRepository extends JpaRepository<EntityClassification, Integer> {
    boolean existsByNameAndEntityIdAndIdIsNot(String name,int entityId,int id);
    List<EntityClassification> findAllByEntityId(Integer entityId);
}
