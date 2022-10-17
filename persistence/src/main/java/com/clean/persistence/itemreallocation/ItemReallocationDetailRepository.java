package com.clean.persistence.itemreallocation;

import com.clean.domain.entity.stc.ItemReallocationDetail;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface ItemReallocationDetailRepository extends JpaRepository<ItemReallocationDetail,Long> {
 List<ItemReallocationDetail> findAllByItemReallocationId(long itemReallocationId);
}
