package com.clean.application.configuration.queries;

import com.clean.application.configuration.models.FiscalYearModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindFiscalYearQuery implements IRequest<FiscalYearModel> {
    private int id;
    private int shamsiYear;
}
