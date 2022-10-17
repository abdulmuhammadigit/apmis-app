package com.clean.domain.entity.conf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "modules", schema = "conf")
public class Modules implements Serializable {
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
    @Column(name = "icon")
    private String icon;

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
    @Column(name = "sorter")
    private int sorter;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "module")
    private java.util.List<Pages> menus;
}
