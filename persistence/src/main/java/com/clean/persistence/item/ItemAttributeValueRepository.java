package com.clean.persistence.item;

import java.util.List;

import com.clean.domain.entity.itm.ItemAttributeValue;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemAttributeValueRepository extends JpaRepository<ItemAttributeValue,Integer> {

	List<ItemAttributeValue> findAllByAttributeId(int attributeId);
	List<ItemAttributeValue> findAllByUnitId(short unitId);
}
