package com.clean.persistence.look;

import com.clean.domain.entity.look.StockKeeperType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockKeeperTypeRepository extends JpaRepository<StockKeeperType,Short> {
}
