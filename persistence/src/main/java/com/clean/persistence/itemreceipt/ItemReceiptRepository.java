package com.clean.persistence.itemreceipt;


import com.clean.domain.entity.stc.ItemReceipt;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemReceiptRepository extends JpaRepository<ItemReceipt, Long> {
    @Query("select R from ItemReceipt R join ItemReceiptDetail D on D.itemReceiptId = R.id where D.id =:itemReceiptDetialId")
    ItemReceipt findByItemReceiptDetailId(@Param("itemReceiptDetialId") long itemReceiptDetailId);
}
