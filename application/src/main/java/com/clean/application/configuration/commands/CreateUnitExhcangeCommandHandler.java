package com.clean.application.configuration.commands;

import com.clean.application.configuration.models.UnitExchangeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.conf.UnitExchange;
import com.clean.persistence.configuration.UnitExchangeRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateUnitExhcangeCommandHandler implements IRequestHandler<CreateUnitExhcangeCommand,UnitExchangeModel> {
    private UnitExchangeRepository repository;
    @Autowired
    public CreateUnitExhcangeCommandHandler(UnitExchangeRepository repository) {
        this.repository = repository;
    }
    @Override
    public UnitExchangeModel handle(CreateUnitExhcangeCommand request) {

        if(request.getUnitId() == request.getToUnitId()){
            throw new RuntimeException("واحد و به واحد یکسان میباشد!");
        }

        if(repository.existsByUnitIdAndToUnitIdAndItemIdAndIdIsNot(request.getUnitId(), request.getToUnitId(),request.getItemId(),request.getId())){
            throw new RuntimeException("از واحد به واحد انتخاب شده از قبل قیمت موجود میباشد!");
        }

        UnitExchange unitExchange = repository.findById(request.getId()).orElse(null);
        if(unitExchange == null){
            unitExchange = new UnitExchange();
        }


        unitExchange.setUnitId(request.getUnitId());
        unitExchange.setQuantity(request.getQuantity());
        unitExchange.setToUnitId(request.getToUnitId());
        unitExchange.setItemId(request.getItemId());
        unitExchange = repository.save(unitExchange);

        return UnitExchangeModel.map(unitExchange);
    }
    
}
