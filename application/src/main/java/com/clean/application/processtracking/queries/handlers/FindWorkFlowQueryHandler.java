package com.clean.application.processtracking.queries.handlers;

import com.clean.application.processtracking.models.WorkFlowModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.WorkFlow;
import com.clean.persistence.processtracking.WorkFlowRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class FindWorkFlowQueryHandler implements IRequestHandler<FindWorkFlowQuery,WorkFlowModel>{
    private WorkFlowRepository workFlowRepository;
    @Autowired
    public FindWorkFlowQueryHandler(WorkFlowRepository workFlowRepository) {
        this.workFlowRepository = workFlowRepository;
    }
    @Override
    public WorkFlowModel handle(FindWorkFlowQuery request) {
        WorkFlow workFlow = workFlowRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("Not Found!"));

        return WorkFlowModel.map(workFlow);
    }
    
}
