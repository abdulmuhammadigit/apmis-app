package com.clean.domain.entity.stc;

import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.look.FiscalYearQuarter;
import com.clean.domain.entity.look.Unit;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
@Entity
@Table(name = "necessity_chart_detail",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class NecessityChartDetail {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "neccessity_chart_id" ,nullable = false)
    private long neccessityChartId;
    @DiffInclude
    @Column(name = "item_detail_id" , nullable = false)
    private int itemDetailId;
    @DiffInclude
    @Column(name = "requested_quantity" , nullable = false)
    private short requestedQuantity;
    @DiffInclude
    @Column(name="base_quantity",nullable = false)
    private short baseQuantity;
    @DiffInclude
    @Column(name = "unit_id" , nullable = false)
    private short unitId;
    @DiffInclude
    @Column(name = "commission_decision")
    private Short commissionDecision;
    @DiffInclude
    @Column(name = "description")
    private String description;
    @DiffInclude
    @Column(name = "fiscal_year_quarter_id" , nullable = false)
    private short fiscalYearQuarterId;
    @DiffInclude
    @Column(name = "submited_quantity")
    private Short submitedQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neccessity_chart_id",referencedColumnName = "id",insertable = false,updatable = false)
    NecessityChart necessityChart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",referencedColumnName = "id",insertable = false,updatable = false)
    ItemDetail itemDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fiscal_year_quarter_id",referencedColumnName = "id", insertable = false,updatable = false)
    FiscalYearQuarter fiscalYearQuarter;
}
