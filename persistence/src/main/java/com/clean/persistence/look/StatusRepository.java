package com.clean.persistence.look;

import com.clean.domain.entity.look.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.util.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> , JpaSpecificationExecutor<Status> {
}
