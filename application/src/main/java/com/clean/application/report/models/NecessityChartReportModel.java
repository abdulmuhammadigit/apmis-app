package com.clean.application.report.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class NecessityChartReportModel {
    private long id;
    private String detail;
    private Integer requestedQuantity;
    private Integer commissionDecisionQuantity;
    private Integer remain;

}
