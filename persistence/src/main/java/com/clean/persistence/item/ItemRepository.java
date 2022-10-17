package com.clean.persistence.item;

import java.util.Collection;
import java.util.List;

import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.itm.ItemDetail;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemRepository extends JpaRepository<Item,Integer> {
    boolean existsByNameAndIdIsNot(String name,int id);
    @Query("select distinct I from Item I join ItemDetail D on I.id = D.itemId where D.id =:itemDetailId ")
    Item findItemByItemDetial(@Param("itemDetailId") int detailId);
    
    List<Item> findAllByCategoryId(short categoryId);



}
