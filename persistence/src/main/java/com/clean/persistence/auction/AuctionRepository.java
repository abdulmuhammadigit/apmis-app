package com.clean.persistence.auction;

import com.clean.domain.entity.stc.Auction;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface AuctionRepository extends JpaRepository<Auction,Long> {
}
