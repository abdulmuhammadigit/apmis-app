package com.clean.persistence.sec;


import com.clean.domain.entity.sec.RolePage;
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
public interface RolePageRepository extends JpaRepository<RolePage, Integer>, JpaSpecificationExecutor<RolePage> {

    List<RolePage> findAllByRoleId(Integer roleId);

    @Query("select rp,r,p from RolePage rp join fetch Roles r on rp.roleId = r.id join fetch Pages p on rp.pageId = p.id where rp.roleId = :roleId")
    List<RolePage> findAllByRoleIdWithRoleAndPage(@Param("roleId") Integer roleId);
}
