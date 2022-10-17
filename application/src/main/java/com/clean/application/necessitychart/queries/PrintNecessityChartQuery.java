package com.clean.application.necessitychart.queries;

import com.clean.application.necessitychart.models.NecessityChartPrintModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintNecessityChartQuery implements IRequest<NecessityChartPrintModel> {
    private long id;
}
