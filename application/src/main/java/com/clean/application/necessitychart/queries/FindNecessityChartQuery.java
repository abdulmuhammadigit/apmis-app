package com.clean.application.necessitychart.queries;

import com.clean.application.necessitychart.models.NecessityChartModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class FindNecessityChartQuery implements IRequest<NecessityChartModel> {
    private long id;
}
