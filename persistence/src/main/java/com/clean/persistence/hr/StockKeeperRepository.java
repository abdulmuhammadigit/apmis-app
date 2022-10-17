package com.clean.persistence.hr;

import com.clean.domain.entity.hr.StockKeeper;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface StockKeeperRepository extends JpaRepository<StockKeeper , Short> {
    @Query("select K , E from StockKeeper K join fetch Employee E on K.employeeId = E.id where K.id =:stockKeeperId")
    StockKeeper findStockKeeperDetail(@Param("stockKeeperId") short Id);

    StockKeeper findByUserId(Long userId);
}
