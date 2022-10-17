package com.clean.persistence.look;

import com.clean.domain.entity.look.OrgUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgUnitTypeRepository extends JpaRepository<OrgUnitType,Integer> {
    @Query("select ot from OrgUnitType ot" +
            " join fetch OrgUnit o on o.orgUnitTypeId=ot.id"+
    " where o.id=:orgUnitId")
    OrgUnitType findByOrgUnitId(@Param("orgUnitId") int orgUnitId);
}
