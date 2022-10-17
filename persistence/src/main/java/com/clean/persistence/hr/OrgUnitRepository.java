package com.clean.persistence.hr;

import com.clean.domain.entity.hr.OrgUnit;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface OrgUnitRepository extends JpaRepository<OrgUnit,Integer> {
    @Query("select O,OO.parentId from OrgUnit O"+
            " left JOIN fetch OrgUnit OO on OO.id = O.parentId"+
            " where O.id=:id")
    OrgUnit findByIdAndParentId(@Param("id") int id);

    @Query("select OO from OrgUnit O"+
            " left JOIN fetch OrgUnit OO on OO.id = O.parentId"+
            " where O.parentId=:id")
    OrgUnit findByIdAndParentText(@Param("id") int id);

    OrgUnit findByParentId(int parentId);
}
