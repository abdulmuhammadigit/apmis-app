package com.clean.persistence.processtracking;

import com.clean.domain.entity.prc.StageType;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface StageTypeRepository extends JpaRepository<StageType,Integer> {
    
}
