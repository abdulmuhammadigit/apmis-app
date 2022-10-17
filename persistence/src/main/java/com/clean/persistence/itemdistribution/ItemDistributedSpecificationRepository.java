package com.clean.persistence.itemdistribution;

import com.clean.domain.entity.stc.ItemDistributedSpecification;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface ItemDistributedSpecificationRepository extends JpaRepository <ItemDistributedSpecification,Long>{
    List<ItemDistributedSpecification> findAllByItemDistributedId(long itemDistributedId);
    void deleteAllByItemDistributedId(long itemDistributedId);
}
