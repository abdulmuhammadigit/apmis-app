package com.clean.application.processtracking.commands.handlers;

import com.clean.application.processtracking.commands.CreateWorkFlowCommand;
import com.clean.application.processtracking.models.WorkFlowModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.WorkFlow;
import com.clean.persistence.processtracking.WorkFlowRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateWorkFlowCommandHandler implements IRequestHandler<CreateWorkFlowCommand,WorkFlowModel>{
    private WorkFlowRepository workFlowRepository;
    @Autowired
    public CreateWorkFlowCommandHandler(WorkFlowRepository workFlowRepository) {
        this.workFlowRepository = workFlowRepository;
    }
    @Override
    public WorkFlowModel handle(CreateWorkFlowCommand request) {
        if(workFlowRepository.existsByNameAndIdIsNot(request.getName(), request.getId())){
            throw new RuntimeException("Work Flow Is Duplicated!");
        }

        if(request.getParentId() != null &&  (request.getId() == request.getParentId())){
            throw new RuntimeException("Parent Is Wrong!");
        }
        
        WorkFlow workFlow = workFlowRepository.findById(request.getId()).orElse(null);
        if(workFlow == null){
            workFlow = new WorkFlow();
        }

        workFlow.setName(request.getName());
        workFlow.setEntityId(request.getEntityId());
        workFlow.setClassificationId(request.getClassificationId());
        workFlow.setParentId(request.getParentId());
        workFlow = workFlowRepository.save(workFlow);
        return WorkFlowModel.map(workFlow);
    }
    
}
