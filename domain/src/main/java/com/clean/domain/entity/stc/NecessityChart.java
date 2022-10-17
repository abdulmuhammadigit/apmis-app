package com.clean.domain.entity.stc;

import com.clean.domain.entity.hr.OrgUnit;
import com.clean.domain.entity.conf.FiscalYear;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "necessity_chart",schema = "stc")
@Getter
@Setter
public class NecessityChart {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "code", nullable = false)
    private String code;
    @DiffInclude
    @Column(name = "org_unit_id" , nullable = false)
    private int orgUnitId;
    @DiffInclude
    @Column(name = "fiscal_year_id" , nullable = false)
    private int fiscalYearId;
    @DiffInclude
    @Column(name = "stage_id")
    private int stageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_unit_id",referencedColumnName = "id",insertable = false,updatable = false)
    OrgUnit orgUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fiscal_year_id",referencedColumnName = "id",insertable = false,updatable = false)
    FiscalYear fiscalYear;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH,mappedBy = "necessityChart")
    private Set<NecessityChartDetail> necessityChartDetails = new HashSet<>();
}
