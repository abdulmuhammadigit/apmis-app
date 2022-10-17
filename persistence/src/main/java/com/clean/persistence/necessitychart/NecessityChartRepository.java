package com.clean.persistence.necessitychart;

import com.clean.domain.entity.stc.NecessityChart;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface NecessityChartRepository extends JpaRepository<NecessityChart, Long> {

   @Query("select N from NecessityChart N join fetch OrgUnit U on N.orgUnitId = U.id join fetch FiscalYear F on N.fiscalYearId = F.id where N.id =:id")
   NecessityChart findNecessityChartDetial(@Param("id") long id);

   boolean existsByFiscalYearIdAndOrgUnitIdAndIdIsNot(int fiscalYearId, int orgUnitId, long id);
}
