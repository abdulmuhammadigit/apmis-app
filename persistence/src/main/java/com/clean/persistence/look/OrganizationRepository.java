package com.clean.persistence.look;
import com.clean.domain.entity.look.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    @Query("SELECT o from Organization o where o.code =:code")
    Organization findByCode(@Param("code") String code);

}
