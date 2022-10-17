package com.clean.persistence.itemrequest;

import com.clean.domain.entity.stc.ItemRequestDetail;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface ItemRequestDetailRepository extends JpaRepository<ItemRequestDetail, Long>, JpaSpecificationExecutor {

        List<ItemRequestDetail> findAllByItemRequestId(long itemRequestId);

        ItemRequestDetail findAllByItemRequest(long itemRequestId);

        boolean existsByItemRequestId(long itemRequestId);

        @Query("select D , ID , U  from ItemRequest R " + "join fetch ItemRequestDetail D on D.itemRequestId = R.id "
                        + "join fetch ItemDetail ID on ID.id = D.itemDetailId "
                        + "join fetch Unit U on U.id = D.unitId "
                        + "left join ItemDistributionDetail DD on DD.itemRequestDetailId = D.id "
                        + "where DD.id is null and R.id =:itemRequestId")
        List<ItemRequestDetail> findAllByItemRequestNotDistributed(@Param("itemRequestId") long itemRequestId);

        @Query("select D , ID , U  from ItemRequest R " + "join fetch ItemRequestDetail D on D.itemRequestId = R.id "
                        + "join fetch ItemDetail ID on ID.id = D.itemDetailId "
                        + "join fetch Unit U on U.id = D.unitId "
                        + "where D.completed =:completed and R.id =:itemRequestId ")
        List<ItemRequestDetail> findAllByItemRequestNotComleted(@Param("completed") boolean completed,
                        @Param("itemRequestId") long itemRequestId);

        boolean existsByItemDetailIdAndItemRequestIdAndIdIsNot(int itemDetailId, long itemRequestId, long id);

        @Query("select R from ItemRequestDetail R join ItemDistributionDetail D on D.itemRequestDetailId = D.id where R.id=:id")
        ItemRequestDetail itemDistributed(@Param("id") long id);
}
