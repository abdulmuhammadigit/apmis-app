package com.clean.persistence.auth;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

import com.clean.domain.entity.sec.PasswordHistory;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
@JaversSpringDataAuditable
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory,Long> {
    
}
