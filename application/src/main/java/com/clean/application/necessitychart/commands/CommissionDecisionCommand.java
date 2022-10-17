package com.clean.application.necessitychart.commands;

import com.clean.application.necessitychart.models.NecessityChartDetailModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class CommissionDecisionCommand implements IRequest<NecessityChartDetailModel> {
    private long necessityChartDetailId;
    private Short commissionDecision;
    private boolean submited;
}
