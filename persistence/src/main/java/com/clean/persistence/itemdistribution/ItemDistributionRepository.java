package com.clean.persistence.itemdistribution;

import antlr.collections.List;
import com.clean.domain.entity.stc.ItemDistribution;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemDistributionRepository extends JpaRepository<ItemDistribution, Long>  {
    @Query("select D,R from ItemDistribution D join fetch ItemRequest R on D.itemRequestId = R.id where D.id=:id")
    ItemDistribution findByIdDetail(@Param("id") long id);
}
