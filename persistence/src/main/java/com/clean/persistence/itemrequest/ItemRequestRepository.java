package com.clean.persistence.itemrequest;

import com.clean.domain.entity.stc.ItemRequest;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {
    @Query("select R,E,O from ItemRequest R join fetch Employee E on R.employeeId = E.id join fetch OrgUnit O on E.orgUnitId = O.id where R.id =:itemRequestId")
    ItemRequest findEmployeeAndOrgUnitDetail(@Param("itemRequestId") long itemRequestId);
}
