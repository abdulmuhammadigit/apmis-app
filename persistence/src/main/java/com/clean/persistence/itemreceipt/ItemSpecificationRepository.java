package com.clean.persistence.itemreceipt;

import com.clean.domain.entity.stc.ItemSpecification;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface ItemSpecificationRepository extends JpaRepository<ItemSpecification,Long> {
    int countByItemReceiptDetailId(long itemReceiptDetailId);
    List<ItemSpecification>  findAllByItemReceiptDetailId(long itemReceiptDetailId);
    List<ItemSpecification> findAllByItemReceiptDetailIdAndStatusId(long itemReceiptDetailId , int statusId);
    ItemSpecification findFirstByItemReceiptDetailIdOrderByTagNumberDesc(long itemReceiptDetailId);

    @Query("SELECT ISP from ItemReceipt IR join ItemReceiptDetail IRD on IR.id = IRD.itemReceiptId join ItemSpecification ISP on ISP.itemReceiptDetailId = IRD.id where IR.id=:ItemReceiptId")
    List<ItemSpecification> findAllByItemReceipt(@Param("ItemReceiptId") long itemReceip);

    @Query("SELECT isp ,ir,ids FROM ItemReallocation ir" +
            " join fetch ItemReallocationDetail ird on ird.itemReallocationId = ir.id" +
            " join fetch ItemReallocationSpecification irs on irs.itemReallocationDetailId = ird.id" +
            " join fetch ItemDistributionDetail idd on idd.id =ird.itemDistributionDetailId" +
            " join fetch ItemDetail idl on idl.id = idd.itemDetailId" +
            " join fetch ItemReceiptDetail ircd on ircd.itemDetailId = idl.id" +
            " join fetch ItemSpecification isp on isp.itemReceiptDetailId = ircd.id" +
            " join fetch ItemDistributedSpecification ids on ids.itemSpecificationId = isp.id"+
            " where ir.id=:ItemReallocationId and ids.statusId=:statusId")
    List<ItemSpecification> findItemDSpecificationByReallocationId(@Param("ItemReallocationId") long ItemReallocationId,@Param("statusId") int statusId);

	boolean existsByItemReceiptDetailId(long id);
}
