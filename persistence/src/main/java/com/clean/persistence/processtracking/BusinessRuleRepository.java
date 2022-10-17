package com.clean.persistence.processtracking;

import com.clean.domain.entity.prc.BusinessRule;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface BusinessRuleRepository extends JpaRepository<BusinessRule,Integer>{
    
}
