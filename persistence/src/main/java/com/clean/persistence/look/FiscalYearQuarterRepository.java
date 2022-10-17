package com.clean.persistence.look;

import com.clean.domain.entity.look.FiscalYearQuarter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiscalYearQuarterRepository extends JpaRepository<FiscalYearQuarter,Short> {
}
