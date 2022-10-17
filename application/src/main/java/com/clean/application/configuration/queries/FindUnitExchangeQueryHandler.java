package com.clean.application.configuration.queries;

import com.clean.application.configuration.models.UnitExchangeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.conf.UnitExchange;
import com.clean.persistence.configuration.UnitExchangeRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class FindUnitExchangeQueryHandler implements IRequestHandler<FindUnitExchangeQuery,UnitExchangeModel> {
    private UnitExchangeRepository repository;
    @Autowired
    public FindUnitExchangeQueryHandler(UnitExchangeRepository repository) {
        this.repository = repository;
    }
    @Override
    public UnitExchangeModel handle(FindUnitExchangeQuery request) {
        
        UnitExchange unitExchange = repository.findById(request.getId()).orElseThrow(()->new RuntimeException("معلومات دریافت نگردید!"));
        return UnitExchangeModel.map(unitExchange);
    }
    
}
