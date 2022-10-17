package com.clean.application.look.quries;

import com.clean.domain.entity.conf.FiscalYear;
import com.clean.persistence.configuration.FiscalYearRepository;
import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchFiscalYearQueryHandler implements IRequestHandler<SearchFiscalYearQuery, List<FiscalYear>> {

    private FiscalYearRepository repository;
    @Autowired
    SearchFiscalYearQueryHandler(FiscalYearRepository repository){
        this.repository = repository;
    }
    @Override
    public List<FiscalYear> handle(SearchFiscalYearQuery request) {
        return repository.findAll();
    }
}
