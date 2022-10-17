package com.clean.persistence.itemreallocation;

import java.util.List;

import com.clean.domain.entity.stc.ItemReallocationSpecification;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemReallocationSpecificationRepository extends JpaRepository<ItemReallocationSpecification,Long> {
    int countByItemReallocationDetailId(long itemReallocationDetailId);
    List<ItemReallocationSpecification> findAllByItemReallocationDetailId(long itemReallocationDetailId);

    @Query("select IRS, ISP , ITD from ItemReallocation IR"+
    " join fetch ItemReallocationDetail IRD on IR.id = IRD.itemReallocationId" +
    " join fetch ItemDistributionDetail IDD on IDD.id = IRD.itemDistributionDetailId"+
    " join fetch ItemDetail ITD on ITD.id = IDD.itemDetailId"+
    " join fetch ItemReallocationSpecification IRS on IRS.itemReallocationDetailId = IRD.id" +
    " join fetch ItemDistributedSpecification IDS on IDS.id = IRS.itemDistributedSpecificationId"+
    " join fetch ItemSpecification ISP on ISP.id = IDS.itemSpecificationId"+
    " where IR.id=:ItemReallocationId and IDS.statusId=:statusId")
    List<ItemReallocationSpecification> findReallocatedSpecificationByReallocationId(@Param("ItemReallocationId") long ItemReallocationId,@Param("statusId") int statusId);

}
