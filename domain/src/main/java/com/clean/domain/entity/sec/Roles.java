package com.clean.domain.entity.sec;
import lombok.*;
import org.javers.core.metamodel.annotation.DiffInclude;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "roles", schema = "sec")
public class Roles implements Serializable {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id", nullable = false)
    private Integer id;

    @DiffInclude
    @Basic
    @Column(name = "name")
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<RolePage> rolePages;
}
