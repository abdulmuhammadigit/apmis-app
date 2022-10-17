package com.clean.application.necessitychart.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NecessityChartSearchModel {
    private long id;
    private String code;
    private int orgUnitId;
    private int fiscalYearId;
    private String orgUnitText;
    private int fiscalYearText;
}
