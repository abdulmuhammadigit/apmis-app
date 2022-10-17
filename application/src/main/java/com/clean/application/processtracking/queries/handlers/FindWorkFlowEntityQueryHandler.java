package com.clean.application.processtracking.queries.handlers;

import com.clean.application.processtracking.models.WorkFlowEntityModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.WorkFlowEntity;
import com.clean.persistence.processtracking.WorkFlowEntityRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;


public class FindWorkFlowEntityQueryHandler implements IRequestHandler<FindWorkFlowEntityQuery,WorkFlowEntityModel> {
    private WorkFlowEntityRepository entityRepository;
    @Autowired
    public FindWorkFlowEntityQueryHandler(WorkFlowEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }
    @Override
    public WorkFlowEntityModel handle(FindWorkFlowEntityQuery request) {
        WorkFlowEntity entity = entityRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("Entity Not Found!"));
        return WorkFlowEntityModel.map(entity);
    }
    
}
