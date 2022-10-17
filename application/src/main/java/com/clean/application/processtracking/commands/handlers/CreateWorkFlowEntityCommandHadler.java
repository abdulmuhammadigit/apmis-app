package com.clean.application.processtracking.commands.handlers;

import com.clean.application.processtracking.commands.CreateWorkFlowEntityCommand;
import com.clean.application.processtracking.models.WorkFlowEntityModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.WorkFlowEntity;
import com.clean.persistence.processtracking.WorkFlowEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateWorkFlowEntityCommandHadler implements IRequestHandler<CreateWorkFlowEntityCommand,WorkFlowEntityModel> {
    private WorkFlowEntityRepository entityRepository;
    @Autowired
    public CreateWorkFlowEntityCommandHadler(WorkFlowEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }
    @Override
    public WorkFlowEntityModel handle(CreateWorkFlowEntityCommand request) {
        WorkFlowEntity entity = entityRepository.findById(request.getId()).orElse(null);
        
        if(entity == null){
            entity = new WorkFlowEntity();
        }

        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity = entityRepository.save(entity);

        return WorkFlowEntityModel.map(entity);
    }
    
}
