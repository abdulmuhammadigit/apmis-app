package com.clean.persistence.itemdistribution;

import com.clean.domain.entity.stc.ItemDistributionDetail;
import com.clean.domain.entity.stc.ItemRequest;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface ItemDistributionDetailRepository extends JpaRepository<ItemDistributionDetail, Long> {

        @Query("select D from ItemDistributionDetail D "
                        + "join fetch ItemRequestDetail R on D.itemRequestDetailId = R.id "
                        + "join fetch ItemDetail I on D.itemDetailId = I.id "
                        + " where D.itemDistributionId =:itemDistributionId ")
        List<ItemDistributionDetail> findAllByItemDistributionId(@Param("itemDistributionId") long itemDistributionId);

        List<ItemDistributionDetail> findAllByItemDistributionIdAndDistributed(long itemDistributionId,
                        boolean distributed);

        boolean existsByItemDistributionId(long itemDistributionId);

        boolean existsByItemDistributionIdAndDistributed(long itemDistributionId, boolean distributed);

        @Query("select DD from ItemRequest I " + "join fetch ItemDistribution D on D.itemRequestId = I.id "
                        + "join fetch ItemDistributionDetail DD on DD.itemDistributionId=D.id"
                        + " where I.employeeId =:employeeId and DD.statusId =:statusId")
        List<ItemDistributionDetail> findAllByItemDistributionByEmployee(@Param("employeeId") int employeeId,
                        @Param("statusId") int statusId);

        boolean existsByItemDistributionIdAndItemDetailIdAndIdIsNot(long itemDistributionId, int itemDetailId, long id);
}
