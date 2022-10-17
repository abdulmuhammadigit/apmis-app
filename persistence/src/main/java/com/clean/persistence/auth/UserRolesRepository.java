package com.clean.persistence.auth;

import java.util.List;

import com.clean.domain.entity.sec.UserRoles;
import com.clean.domain.entity.sec.UserRolesPK;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
@JaversSpringDataAuditable
public interface UserRolesRepository extends JpaRepository<UserRoles, UserRolesPK>, JpaSpecificationExecutor<UserRoles> {
    List<UserRoles> findAllByUserId(Long userID);
}
