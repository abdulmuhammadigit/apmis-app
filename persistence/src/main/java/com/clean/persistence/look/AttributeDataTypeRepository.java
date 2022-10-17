package com.clean.persistence.look;

import com.clean.domain.entity.look.AttributeDataType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeDataTypeRepository extends JpaRepository<AttributeDataType,Short> {
}
