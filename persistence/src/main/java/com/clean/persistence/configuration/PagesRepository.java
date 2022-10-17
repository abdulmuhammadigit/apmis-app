//FiscalYear
package com.clean.persistence.configuration;

import com.clean.domain.entity.conf.Pages;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Repository
@Scope(WebApplicationContext.SCOPE_REQUEST)
public interface PagesRepository extends JpaRepository<Pages,Integer>, JpaSpecificationExecutor<Pages> {

    List<Pages> findByParentId(@Param("parentId") Integer parentId, Sort sort);
    List<Pages> findByModuleId(@Param("moduleId") Integer moduleId);




}
