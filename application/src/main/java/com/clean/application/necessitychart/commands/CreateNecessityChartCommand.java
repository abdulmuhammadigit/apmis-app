package com.clean.application.necessitychart.commands;

import com.clean.application.necessitychart.models.NecessityChartModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;


@Data
public class CreateNecessityChartCommand implements IRequest<NecessityChartModel> {
    private long id;
    private String code;
    private int orgUnitId;
    private int fiscalYearId;
}
