package com.clean.domain.entity.hr;

import com.clean.domain.entity.stc.NecessityChart;
import com.clean.domain.entity.look.OrgUnitType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="org_unit",schema = "hr")
@Getter
@Setter
@NoArgsConstructor
public class OrgUnit {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "org_unit_type_id" , nullable = false)
    private int orgUnitTypeId;
    @DiffInclude
    @Column(name = "name" , nullable = false)
    private String name;
    @DiffInclude
    @Column(name="parent_id")
    private Integer parentId;
    @DiffInclude
    @Column(name = "sorter")
    private String sorter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_unit_type_id",referencedColumnName = "id",insertable = false,updatable = false)
    OrgUnitType orgUnitType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",referencedColumnName = "id",insertable = false,updatable = false)
    OrgUnit orgUnit;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "orgUnit")
    private Set<Employee> employees = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "orgUnit")
    private Set<NecessityChart> necessityCharts = new HashSet<>();
}
