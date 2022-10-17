package com.clean.application.necessitychart.models;

import com.clean.domain.entity.stc.NecessityChartDetail;
import lombok.Data;


@Data

public class NecessityChartDetailModel {
    private long id;
    private long neccessityChartId;
    private int itemDetailId;
    private short requestedQuantity;
    private short baseQuantity;
    private short unitId;
    private Short commissionDecision;
    private String description;
    private short fiscalYearQuarterId;
    private Short submitedQuantity;
    private String unitText;
    private String itemDetailText;
    private String fiscalYearQuarterText;
    private String baseUnitText;
    public static NecessityChartDetailModel map(NecessityChartDetail necessityChartDetail)
    {
        NecessityChartDetailModel model = new NecessityChartDetailModel();
        model.setId(necessityChartDetail.getId());
        model.setNeccessityChartId(necessityChartDetail.getNeccessityChartId());
        model.setItemDetailId(necessityChartDetail.getItemDetailId());
        model.setRequestedQuantity(necessityChartDetail.getRequestedQuantity());
        model.setBaseQuantity(necessityChartDetail.getBaseQuantity());
        model.setUnitId(necessityChartDetail.getUnitId());
        model.setCommissionDecision(necessityChartDetail.getCommissionDecision());
        model.setDescription(necessityChartDetail.getDescription());
        model.setFiscalYearQuarterId(necessityChartDetail.getFiscalYearQuarterId());
        model.setSubmitedQuantity(necessityChartDetail.getSubmitedQuantity());
        return model;
    }
}
