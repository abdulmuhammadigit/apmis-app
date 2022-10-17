package com.clean.persistence.item;

import com.clean.domain.entity.itm.Attribute;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface AttributeRepository extends JpaRepository<Attribute,Integer>, JpaSpecificationExecutor<Attribute> {
    boolean existsByNameAndCategoryIdAndIdIsNot(String name,short categoryId,int id);
    @Query("select A from Attribute A join ItemAttribute IA on IA.attributeId = A.id where IA.itemId =:itemId")
    List<Attribute> findAllByItemId(@Param("itemId") int itemId);
}
