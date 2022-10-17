package com.clean.application.necessitychart.commands;

import com.clean.application.necessitychart.models.NecessityChartDetailModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNecessityChartDetailCommand implements IRequest<NecessityChartDetailModel>{
    private long id;
    private long necessityChartId;
    private int itemDetailId;
    private short requestedQuantity;
    private short baseQuantity;
    private short unitId;
    private Short commissionDecision;
    private String description;
    private short fiscalYearQuarterId;

}
