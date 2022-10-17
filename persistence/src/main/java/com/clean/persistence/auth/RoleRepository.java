package com.clean.persistence.auth;

import java.util.List;

import com.clean.domain.entity.sec.Roles;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;


@Repository
@JaversSpringDataAuditable
public interface RoleRepository extends JpaRepository<Roles, Integer> {
//    Optional<UserRoles> findByName(String name);
    List<Roles> findByNameContains(String name);
}
