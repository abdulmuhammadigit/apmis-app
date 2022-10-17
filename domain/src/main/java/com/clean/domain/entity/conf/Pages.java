package com.clean.domain.entity.conf;

import com.clean.domain.entity.sec.PageEntity;
import com.clean.domain.entity.sec.RolePage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pages", schema = "conf")
public class Pages implements Serializable {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id", nullable = false)
    private int id;

    @DiffInclude
    @Basic
    @Column(name = "title", nullable = false)
    private String title;

    @DiffInclude
    @Basic
    @Column(name = "icon", nullable = false)
    private String icon;

    @DiffInclude
    @Basic
    @Column(name = "abbr", nullable = false)
    private String abbr;

    @DiffInclude
    @Basic
    @Column(name = "parent_id")
    private Integer parentId;

    @DiffInclude
    @Basic
    @Column(name = "sorter")
    private String sorter;

    @DiffInclude
    @Basic
    @Column(name = "page")
    private String page;

    @DiffInclude
    @Basic
    @Column(name = "description")
    private String description;

    @DiffInclude
    @Basic
    @Column(name = "module_id")
    private Integer moduleId;

    @DiffInclude
    @Basic
    @Column(name = "toggle")
    private String toggle;

    @DiffInclude
    @Basic
    @Column(name = "root", nullable = false)
    private boolean root;

    @DiffInclude
    @Basic
    @Column(name = "permission")
    private String permission;

    @DiffInclude
    @Basic
    @Column(name = "alignment")
    private String alignment;

    @DiffInclude
    @Basic
    @Column(name = "translate")
    private String translate;

    @DiffInclude
    @Basic
    @Column(name = "bullet")
    private String bullet;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id",columnDefinition = "module_id",updatable = false,insertable = false)
    private Modules module;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(referencedColumnName = "id",columnDefinition = "parent_id",updatable = false,insertable = false)
    private Pages parent;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
    private java.util.List<Pages> submenu;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
    private Set<RolePage> rolePages;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
    private Set<PageEntity> pageEntities;
}
