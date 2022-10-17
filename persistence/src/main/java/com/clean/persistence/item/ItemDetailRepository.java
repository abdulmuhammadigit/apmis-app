package com.clean.persistence.item;

import java.util.List;

import com.clean.domain.entity.itm.ItemDetail;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemDetailRepository extends JpaRepository<ItemDetail,Integer> {
    List<ItemDetail> findAllByItemId(int itemId);
}
