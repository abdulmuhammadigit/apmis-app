package com.clean.persistence.item;

import java.util.List;

import com.clean.domain.entity.itm.ItemAttribute;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemAttributeRespository extends JpaRepository<ItemAttribute,Integer> {
    boolean existsByItemIdAndIdIsNot(int itemId,int id);

	List<ItemAttribute> findAllByAttributeId(int id);

	boolean existsByItemIdAndAttributeId(int itemId,int attributeId);
}
