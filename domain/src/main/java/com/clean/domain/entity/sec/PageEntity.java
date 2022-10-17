package com.clean.domain.entity.sec;

import com.clean.domain.entity.conf.Pages;
import lombok.*;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "page_entity", schema = "sec")
@IdClass(PageEntityPK.class)
public class PageEntity implements Serializable {
    @DiffInclude
    @Id
    @Basic
    @Column(name = "page_id", nullable = false)
    private Integer pageId;

    @DiffInclude
    @Id
    @Basic
    @Column(name = "entity_id", nullable = false)
    private Integer entityId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Pages page;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Entity entity;
}
