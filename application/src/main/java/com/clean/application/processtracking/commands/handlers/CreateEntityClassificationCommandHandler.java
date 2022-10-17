package com.clean.application.processtracking.commands.handlers;

import com.clean.application.processtracking.commands.CreateEntityClassificationCommand;
import com.clean.application.processtracking.models.EntityClassificationModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.EntityClassification;
import com.clean.persistence.processtracking.EntityClassificationRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateEntityClassificationCommandHandler implements IRequestHandler<CreateEntityClassificationCommand,EntityClassificationModel> {
    private EntityClassificationRepository classificationRepository;
    @Autowired
    public CreateEntityClassificationCommandHandler(EntityClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }
    @Override
    public EntityClassificationModel handle(CreateEntityClassificationCommand request) {

        if(classificationRepository.existsByNameAndEntityIdAndIdIsNot(request.getName(), request.getEntityId(), request.getId())){
            throw new RuntimeException("Classification Exist For Entity!");
        }

        EntityClassification classification = classificationRepository.findById(request.getId()).orElse(null);
        if(classification == null){
            classification = new EntityClassification();
        }
        classification.setName(request.getName());
        classification.setEntityId(request.getEntityId());
        classification = classificationRepository.save(classification);
        return EntityClassificationModel.map(classification);
    }
    
}
