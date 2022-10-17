package com.clean.persistence.sec;

import com.clean.domain.entity.sec.Entity;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

@Repository
@JaversSpringDataAuditable
@Scope(WebApplicationContext.SCOPE_REQUEST)
public interface EntityRepository extends JpaRepository<Entity,Integer>, JpaSpecificationExecutor<Entity> { }
