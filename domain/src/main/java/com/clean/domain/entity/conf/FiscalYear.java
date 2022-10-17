package com.clean.domain.entity.conf;

import com.clean.domain.entity.stc.NecessityChart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fiscal_year",schema = "conf")
@Getter
@Setter
@NoArgsConstructor
public class FiscalYear {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "shamsi_year" ,nullable = false)
    private int shamsiYear;
    @DiffInclude
    @Column(name = "start_date" ,nullable = false)
    private Date startDate;
    @DiffInclude
    @Column(name = "end_date" , nullable = false)
    private Date endDate;
    @DiffInclude
    @Column(name = "current_fiscal_year" , nullable = false)
    private boolean currentFiscalYear;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "orgUnit")
    private Set<NecessityChart> necessityCharts = new HashSet<>();
}
