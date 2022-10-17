package com.clean.application.processtracking.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.processtracking.models.WorkFlowModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.processtracking.WorkFlowRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class GetWorkFlowQueryHandler implements IRequestHandler<GetWorkFlowQuery,List<WorkFlowModel>> {
    private WorkFlowRepository workFlowRepository;
    @Autowired
    public GetWorkFlowQueryHandler(WorkFlowRepository workFlowRepository) {
        this.workFlowRepository = workFlowRepository;
    }
    @Override
    public List<WorkFlowModel> handle(GetWorkFlowQuery request) {
        return  workFlowRepository.findAll().stream().map(map->{
            WorkFlowModel model = new WorkFlowModel();
            model.setId(map.getId());
            model.setName(map.getName());
            return model;
        }).collect(Collectors.toList());
    }
    
}
