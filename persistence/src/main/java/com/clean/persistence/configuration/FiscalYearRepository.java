package com.clean.persistence.configuration;

import java.sql.Date;

import com.clean.domain.entity.conf.FiscalYear;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface FiscalYearRepository extends JpaRepository<FiscalYear,Integer> {
    FiscalYear findByCurrentFiscalYear(boolean currentYear);

    @Query("select F from FiscalYear F where F.startDate <=:currentDate and F.endDate >=:currentDate")
    FiscalYear findCurrentYear(@Param("currentDate") Date currentDate);

    FiscalYear findByShamsiYear(int shamsiYear);

    @Query("select F from FiscalYear F where F.endDate >=:startDate and F.id !=:id") 
    FiscalYear existsByStartDate(@Param("startDate") Date startDate,@Param("id") int id);
}
