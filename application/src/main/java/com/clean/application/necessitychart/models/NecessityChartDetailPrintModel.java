package com.clean.application.necessitychart.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NecessityChartDetailPrintModel {
    private int itemDetailId;
    private String itemDetailText;
    private String unitText;
    private int firstQuarter;
    private int secondQuarter;
    private int thirdQuarter;
    private int fourthQuarter;
}