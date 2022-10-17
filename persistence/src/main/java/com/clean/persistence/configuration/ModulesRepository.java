//FiscalYear
package com.clean.persistence.configuration;

import com.clean.domain.entity.conf.FiscalYear;
import com.clean.domain.entity.conf.Modules;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Repository

@Scope(WebApplicationContext.SCOPE_REQUEST)
public interface ModulesRepository extends JpaRepository<Modules,Integer>, JpaSpecificationExecutor<Modules> {

}
