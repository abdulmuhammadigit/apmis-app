package com.clean.domain.entity.sec;

import lombok.*;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "role_page_entity_permission", schema = "sec")
@IdClass(RolePageEntityPermissionPK.class)
public class RolePageEntityPermission implements Serializable {
    @DiffInclude
    @Id
    @Basic
    @Column(name = "role_page_id", nullable = false)
    private Integer rolePageId;

    @DiffInclude
    @Id
    @Basic
    @Column(name = "entity_id", nullable = false)
    private Integer entityId;

    @DiffInclude
    @Id
    @Basic
    @Column(name = "entity_permission_id", nullable = false)
    private Integer entityPermissionId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_permission_id", referencedColumnName = "id", insertable = false, updatable = false)
    private EntityPermission entityPermission;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_page_id", referencedColumnName = "id", insertable = false, updatable = false)
    private RolePage rolePage;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Entity entity;
}
