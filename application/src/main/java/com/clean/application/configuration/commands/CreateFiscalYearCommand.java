package com.clean.application.configuration.commands;

import java.sql.Date;

import com.clean.application.configuration.models.FiscalYearModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFiscalYearCommand implements IRequest<FiscalYearModel>{
    private int id;
    private int shamsiYear;
    private Date startDate;
    private Date endDate;
}
