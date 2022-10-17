package com.clean.application.report.queries;

import com.clean.application.report.models.NecessityChartReportModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetNecessityChartReportQuery implements IRequest<List<NecessityChartReportModel>> {
    private int orgUnitId;
    private int fiscalYearId;

}
