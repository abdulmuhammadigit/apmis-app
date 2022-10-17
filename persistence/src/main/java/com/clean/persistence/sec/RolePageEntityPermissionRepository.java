package com.clean.persistence.sec;

import com.clean.domain.entity.sec.RolePage;
import com.clean.domain.entity.sec.RolePageEntityPermission;
import com.clean.domain.entity.sec.RolePageEntityPermissionPK;
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
public interface RolePageEntityPermissionRepository extends JpaRepository<RolePageEntityPermission, RolePageEntityPermissionPK>, JpaSpecificationExecutor<RolePageEntityPermission> {
    @Query("select rp from RolePageEntityPermission  rp where rp.rolePageId = :pageId and rp.entityId = :entityId ")
    List<RolePageEntityPermission> findAllByRolePageIdAndEntityId(@Param("pageId")Integer rolePageId,@Param("entityId")Integer entityId);
}
