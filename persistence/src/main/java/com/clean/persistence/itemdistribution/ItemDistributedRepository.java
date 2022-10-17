package com.clean.persistence.itemdistribution;

import com.clean.domain.entity.stc.ItemDistributed;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface ItemDistributedRepository extends JpaRepository<ItemDistributed, Long> {
    List<ItemDistributed> findAllByItemDistributionDetailId(long itemDistributionDetailId);

    @Query("select ID from ItemDistribution D join ItemDistributionDetail DD on D.id = DD.itemDistributionId join ItemDistributed ID on ID.itemDistributionDetailId = DD.id where D.id =:itemDistributionId")
    ItemDistributed distributedAny(@Param("itemDistributionId") long itemDistributionId);


    @Query("SELECT distinct sum(ID.quantity) from ItemDistributed ID  " +
            "where ID.id=:itemDistributionId")
    int countOfDistributedItem(@Param("itemDistributionId") long itemDistributionId);

}
