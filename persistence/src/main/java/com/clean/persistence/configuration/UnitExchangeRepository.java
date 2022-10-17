package com.clean.persistence.configuration;

import java.util.List;

import com.clean.domain.entity.conf.UnitExchange;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface UnitExchangeRepository extends JpaRepository<UnitExchange , Integer> {
    List<UnitExchange> findAllByUnitIdAndToUnitId(int unitId,int toUnitId);
    boolean existsByUnitIdAndToUnitIdAndItemIdAndIdIsNot(int unitId,int toUnitId,Integer itemId,int id);
}
