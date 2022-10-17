package com.clean.application.look.quries;

import com.clean.domain.entity.look.FiscalYearQuarter;
import com.clean.persistence.look.FiscalYearQuarterRepository;
import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchFiscalYearQuarterQueryHandler implements IRequestHandler<SearchFiscalYearQuarterQuery, List<FiscalYearQuarter>> {
    private FiscalYearQuarterRepository repository;
    @Autowired
    SearchFiscalYearQuarterQueryHandler(FiscalYearQuarterRepository repository){
        this.repository = repository;
    }
    @Override
    public List<FiscalYearQuarter> handle(SearchFiscalYearQuarterQuery request) {
        return repository.findAll();
    }
}
