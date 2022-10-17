package com.clean.persistence.auth;

import com.clean.domain.entity.sec.PasswordRequest;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface PasswordRequestRepository extends JpaRepository<PasswordRequest,Long> {
    boolean existsByEmailAndProcessed(String email,Boolean processed);
}
