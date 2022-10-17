package com.clean.persistence.look;

import com.clean.domain.entity.look.Unit;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Short> , JpaSpecificationExecutor<Unit> {
    @Query("select U from Unit U join Item I on I.unitId = U.id where I.id =:itemId")
    Unit findByItemId(@Param("itemId") int itemId);

    boolean existsByNameAndIdIsNot(String name,short id);
}
