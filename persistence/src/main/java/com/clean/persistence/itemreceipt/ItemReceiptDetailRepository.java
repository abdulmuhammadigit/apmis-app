package com.clean.persistence.itemreceipt;

import com.clean.domain.entity.stc.ItemReceiptDetail;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface ItemReceiptDetailRepository extends JpaRepository<ItemReceiptDetail, Long> {
    @Query("select IR,ID from ItemReceiptDetail IR join fetch ItemDetail ID on IR.itemDetailId = ID.id where IR.itemReceiptId=:itemReceiptId ")
    List<ItemReceiptDetail> findByItemReceiptId(@Param("itemReceiptId") long itemReceiptId);

    @Query("select R from ItemReceiptDetail R join fetch ItemReceipt D on R.itemReceiptId = D.id  where D.stockKeeperId=:stockKeeper and R.itemDetailId =:itemDetail and R.finished =:finish and D.statusId=:statusId order by D.receiveDate desc")
    List<ItemReceiptDetail> findByStockKeeperAndItemDetail(@Param("stockKeeper") short stockKeeperId,@Param("itemDetail") int itemDetailId , boolean finish ,@Param("statusId") int statusId );

    @Query("select IR from ItemReceiptDetail IR join fetch ItemDistributed I on I.itemReceiptDetailId= IR.id where I.itemDistributionDetailId=:itemDistributionDetailId")
    ItemReceiptDetail findByItemDistributionDetail(@Param("itemDistributionDetailId") long itemDistributionDetailId);

    boolean existsByItemReceiptIdAndItemDetailIdAndIdIsNot(long itemReceiptId,int itemDetailId , long id);

    boolean existsByItemReceiptId(long itemReceiptId);
    
    @Query("SELECT distinct sum(IR.remain) from ItemReceiptDetail IR join ItemDetail D on IR.itemDetailId=D.id join Item I on I.id=D.itemId " +
            "where I.id=:itemId")
    Integer countOfItem(@Param("itemId") int itemId);

    @Query("SELECT distinct sum(IR.remain) from ItemReceiptDetail IR " +
            "where IR.itemDetailId=:itemDetailId")
    Integer countOfItemDetail(@Param("itemDetailId") int itemDetailId);

    @Query("SELECT count(RD.itemReceiptId)as totalIemReceipt from ItemReceiptDetail RD join ItemReceipt IR on IR.id=RD.itemReceiptId join ItemReceiptType RT on RT.id=IR.itemReceiptTypeId " +
    "group by IR.itemReceiptTypeId , RT.name")
    int countOfItemReceipt();

    @Query("SELECT sum(RD.quantity)as totalItem from ItemReceiptDetail RD join ItemReceipt IR on IR.id=RD.itemReceiptId join ItemReceiptType RT on RT.id=IR.itemReceiptTypeId " +
    "group by IR.itemReceiptTypeId , RT.name")
    int sumOfItem();

    @Query("SELECT sum(RD.price * RD.quantity)as totalPrice from ItemReceiptDetail RD join ItemReceipt IR on IR.id=RD.itemReceiptId join ItemReceiptType RT on RT.id=IR.itemReceiptTypeId " +
    "group by IR.itemReceiptTypeId , RT.name")
    double sumOfPrice();

//    @Query("SELECT " +
//            " RT.name ," +
//            " count(distinct RD.itemReceiptId) as totalItemReceipt , " +
//            " sum(RD.quantity) as totalItem , " +
//            " sum(RD.price * RD.quantity) as totalPrice " +
//            " from ItemReceiptDetail RD " +
//            " join ItemReceipt IR on IR.id=RD.itemReceiptId " +
//            " join ItemReceiptType RT on RT.id=IR.itemReceiptTypeId " +
//            "group by IR.itemReceiptTypeId , RT.name")
//    object getReportByItemReceiptType();

}
