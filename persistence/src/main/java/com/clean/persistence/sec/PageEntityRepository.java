package com.clean.persistence.sec;

import com.clean.domain.entity.sec.Entity;
import com.clean.domain.entity.sec.PageEntity;
import com.clean.domain.entity.sec.PageEntityPK;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Repository
@JaversSpringDataAuditable
@Scope(WebApplicationContext.SCOPE_REQUEST)
public interface PageEntityRepository extends JpaRepository<PageEntity, PageEntityPK>, JpaSpecificationExecutor<PageEntity> {
    @Query("select pe,e from PageEntity pe join fetch Entity e on pe.entityId = e.id  where pe.pageId = :pageId")
    List<PageEntity> findAllByPageIdWithEntityAndPermissions(@Param("pageId") Integer pageId);
}
