package com.clean.application.configuration.queries;

import com.clean.application.configuration.models.FiscalYearModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.conf.FiscalYear;
import com.clean.persistence.configuration.FiscalYearRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class FindFiscalYearQueryHandler implements IRequestHandler<FindFiscalYearQuery,FiscalYearModel> {
    private FiscalYearRepository repository;
    @Autowired
    public FindFiscalYearQueryHandler(FiscalYearRepository fiscalYearRepository) {
        this.repository = fiscalYearRepository;
    }
    @Override
    public FiscalYearModel handle(FindFiscalYearQuery request) {
        FiscalYear fiscalYear = repository.findById(request.getId()).orElseThrow(()->new RuntimeException("سال مالی پیدا شد!"));

        return FiscalYearModel.map(fiscalYear);
    }
    
}
