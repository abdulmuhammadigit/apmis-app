package com.clean.application.look.quries;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.look.Unit;
import com.clean.persistence.look.UnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUnitQueryHandler implements IRequestHandler<FindUnitQuery,Unit>{
    private UnitRepository unitRepository;
    @Autowired
    public FindUnitQueryHandler(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }
    @Override
    public Unit handle(FindUnitQuery request) {
        return unitRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("واحد دریافت نگردید!"));
    }
    
}
