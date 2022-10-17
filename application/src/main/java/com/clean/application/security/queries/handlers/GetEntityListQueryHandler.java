package com.clean.application.security.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.security.models.EntityModel;
import com.clean.application.security.queries.GetEntityListQuery;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.sec.EntityRepository;

public class GetEntityListQueryHandler implements IRequestHandler<GetEntityListQuery, List<EntityModel>> {
    private EntityRepository entityRepository;

    public GetEntityListQueryHandler(
        EntityRepository entityRepository
    ){
        this.entityRepository = entityRepository;
    }

    @Override
    public List<EntityModel> handle(GetEntityListQuery request) {
        
        return this.entityRepository.findAll().stream().map(EntityModel::Map).collect(Collectors.toList());
    }
    
}
