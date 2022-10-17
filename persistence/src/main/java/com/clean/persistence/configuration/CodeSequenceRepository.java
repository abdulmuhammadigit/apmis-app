package com.clean.persistence.configuration;

import com.clean.domain.entity.conf.CodeSequence;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface CodeSequenceRepository extends JpaRepository<CodeSequence,Long> {

    @Query("SELECT s from CodeSequence s where s.form =:formType and s.fiscalYearId =:year")
    CodeSequence getByFormOrYear(@Param("formType") String formType, @Param("year") int year);

}
