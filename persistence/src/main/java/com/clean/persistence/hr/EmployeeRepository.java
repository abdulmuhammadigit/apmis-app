package com.clean.persistence.hr;

import com.clean.domain.entity.hr.Employee;
import com.clean.domain.entity.stc.ItemReallocation;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query("SELECT distinct e FROM ItemReallocation ir " +
            "JOIN fetch ItemReallocationDetail ird on ird.itemReallocationId = ir.id" +
            " join fetch ItemDistributionDetail idd on idd.id =ird.itemDistributionDetailId" +
            " join fetch ItemRequestDetail irqd on irqd.id = idd.itemRequestDetailId" +
            " join fetch ItemRequest irq  on irq.id = irqd.itemRequestId"+
            " join fetch Employee e on e.id=irq.employeeId"+
            " where ir.id =:itemReallocationId")
    Employee findByEmployeeId(@Param("itemReallocationId") long itemReallocationId);
}
