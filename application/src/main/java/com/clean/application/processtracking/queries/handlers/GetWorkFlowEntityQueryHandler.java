package com.clean.application.processtracking.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.processtracking.models.WorkFlowEntityModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.processtracking.WorkFlowEntityRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class GetWorkFlowEntityQueryHandler implements IRequestHandler<GetWorkFlowEntityQuery,List<WorkFlowEntityModel>> {
    private WorkFlowEntityRepository workFlowEntityRepository;
    @Autowired
    public GetWorkFlowEntityQueryHandler(WorkFlowEntityRepository workFlowEntityRepository) {
        this.workFlowEntityRepository = workFlowEntityRepository;
    }
    @Override
    public List<WorkFlowEntityModel> handle(GetWorkFlowEntityQuery request) {
        return workFlowEntityRepository.findAll().stream().map(WorkFlowEntityModel::map).collect(Collectors.toList());
        
    }
    
}
