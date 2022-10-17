package com.clean.application.necessitychart.queries;

import com.clean.application.necessitychart.models.NecessityChartModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindCommissionDecisionQuery implements IRequest<NecessityChartModel> {
    private long id;
}
