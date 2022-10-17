package com.clean.persistence.sec;

import com.clean.domain.entity.sec.EntityPermission;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;


@Repository
@JaversSpringDataAuditable
@Scope(WebApplicationContext.SCOPE_REQUEST)
public interface EntityPermissionRepository extends JpaRepository<EntityPermission,Integer>, JpaSpecificationExecutor<EntityPermission> {

}
