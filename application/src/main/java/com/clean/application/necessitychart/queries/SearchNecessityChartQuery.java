package com.clean.application.necessitychart.queries;

import com.clean.application.necessitychart.models.NecessityChartSearchModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

import java.util.List;
@Data
public class SearchNecessityChartQuery implements IRequest<List<NecessityChartSearchModel>> {
    private String code;
    private int orgUnitId;
    private int fiscalYearId;
    private boolean allocatedSearch;
 }
