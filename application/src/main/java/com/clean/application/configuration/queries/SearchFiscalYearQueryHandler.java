package com.clean.application.configuration.queries;

import com.clean.application.configuration.models.FiscalYearModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.conf.FiscalYear;
import com.clean.persistence.configuration.FiscalYearRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchFiscalYearQueryHandler implements IRequestHandler<SearchFiscalYearQuery,FiscalYearModel> {
    private FiscalYearRepository fiscalYearRepository;
    @Autowired
    public SearchFiscalYearQueryHandler(FiscalYearRepository fiscalYearRepository){
        this.fiscalYearRepository = fiscalYearRepository;
    }
    @Override
    public FiscalYearModel handle(SearchFiscalYearQuery request) {
       FiscalYear fiscalYear =fiscalYearRepository.findByShamsiYear(request.getShamsiYear());
        if(fiscalYear == null){
            throw new RuntimeException("سال مالی دریافت نگردید");
        }
        return FiscalYearModel.map(fiscalYear);
    }
    
}
