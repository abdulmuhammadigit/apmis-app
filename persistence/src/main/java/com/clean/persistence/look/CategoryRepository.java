package com.clean.persistence.look;

import com.clean.domain.entity.look.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Short>{
    boolean existsByName(String name);
}
