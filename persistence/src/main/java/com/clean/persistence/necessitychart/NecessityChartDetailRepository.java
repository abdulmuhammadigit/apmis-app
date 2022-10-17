package com.clean.persistence.necessitychart;

import com.clean.domain.entity.stc.NecessityChartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface NecessityChartDetailRepository extends JpaRepository<NecessityChartDetail, Long> {

    List<NecessityChartDetail> findByNecessityChartId(long necessityChartId);

	boolean existsByItemDetailIdAndFiscalYearQuarterIdAndIdIsNot(int itemDetailId, short fiscalYearQuarterId, long id);

	boolean existsByNecessityChartId(long recordId);

	boolean existsByNecessityChartIdAndCommissionDecisionIsNull(long necessityChartId);
}

