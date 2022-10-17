package com.clean.application.necessitychart.models;

import com.clean.domain.entity.stc.NecessityChart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NecessityChartModel {
    private long id;
    private String code;
    private int orgUnitId;
    private int fiscalYearId;
    private int stageId;
    private String orgUnitText;
    private int fiscalYearText;
    private List<NecessityChartDetailModel> detailModelList;
    public static NecessityChartModel map(NecessityChart necessityChart)
    {
        NecessityChartModel model = new NecessityChartModel();
        model.setId(necessityChart.getId());
        model.setCode(necessityChart.getCode());
        model.setOrgUnitId(necessityChart.getOrgUnitId());
        model.setFiscalYearId(necessityChart.getFiscalYearId());
        model.setStageId(necessityChart.getStageId());
        return model;
    }

}
