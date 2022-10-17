package com.clean.domain.entity.look;

import com.clean.domain.entity.stc.NecessityChartDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fiscal_year_quarter",schema = "look")
@Getter
@Setter
@NoArgsConstructor
public class FiscalYearQuarter {
    @Id
    @Column(name = "id" ,nullable = false)
    private short id;
    @Column(name = "name" , nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "fiscalYearQuarter")
    private Set<NecessityChartDetail> necessityCharts = new HashSet<>();
}
