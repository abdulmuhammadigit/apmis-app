package com.clean.persistence.itemreallocation;

import com.clean.domain.entity.stc.ItemReallocation;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemReallocationRepository extends JpaRepository<ItemReallocation,Long> {
}
