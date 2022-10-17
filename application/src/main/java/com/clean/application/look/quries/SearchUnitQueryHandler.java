package com.clean.application.look.quries;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.look.Unit;
import com.clean.persistence.look.UnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class SearchUnitQueryHandler implements IRequestHandler<SearchUnitQuery, List<Unit>> {

    private UnitRepository repository;
    @Autowired
    SearchUnitQueryHandler(UnitRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Unit> handle(SearchUnitQuery request) {
        Specification specification = null;
        if(request.getName() != null && !request.getName().isEmpty()){
            specification = Specification.where((Specification<Unit>) (root , query , criterail)->criterail.like(root.get("name"), MessageFormat.format("%{0}%", request.getName())));
        }

        if(specification == null){
            return repository.findAll();
        }

        return repository.findAll(specification);
    }
}
