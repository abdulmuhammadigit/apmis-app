package com.clean.domain.entity.sec;

import lombok.*;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "entity", schema = "sec")
public class Entity implements Serializable {
    @DiffInclude
    @Id
    @Basic
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @DiffInclude
    @Basic
    @Column(name = "code")
    private String code;

    @DiffInclude
    @Basic
    @Column(name = "title")
    private String title;

    @DiffInclude
    @Basic
    @Column(name = "path")
    private String path;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entity")
    private Set<PageEntity> pageEntities;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entity")
    private Set<RolePageEntityPermission> rolePageEntityPermissions;

}
