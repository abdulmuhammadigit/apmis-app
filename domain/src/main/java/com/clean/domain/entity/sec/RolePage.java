package com.clean.domain.entity.sec;

import com.clean.domain.entity.conf.Pages;
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
@Table(name = "role_page", schema = "sec")
public class RolePage implements Serializable {

    @DiffInclude
    @Id
    @Basic
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @DiffInclude
    @Basic
    @Column(name = "page_id", nullable = false)
    private Integer pageId;

    @DiffInclude
    @Basic
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Roles role;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Pages page;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rolePage")
    private Set<RolePageEntityPermission> rolePageEntityPermissions;
}
