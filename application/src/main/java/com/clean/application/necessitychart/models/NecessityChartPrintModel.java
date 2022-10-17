package com.clean.application.necessitychart.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NecessityChartPrintModel {
    private String code;
    private String orgUnitText;
    private int fiscalYear;
    private List<NecessityChartDetailPrintModel> detailPrintModelList;
}
