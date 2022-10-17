package com.clean.application.processtracking.queries.handlers;

import com.clean.application.processtracking.models.EntityClassificationModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.EntityClassification;
import com.clean.persistence.processtracking.EntityClassificationRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class FindEntityClassificationQueryHandler implements IRequestHandler<FindEntityClassificationQuery,EntityClassificationModel> {
    private EntityClassificationRepository classificationRepository;
    @Autowired
    public FindEntityClassificationQueryHandler(EntityClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }
    @Override
    public EntityClassificationModel handle(FindEntityClassificationQuery request) {
        EntityClassification  classification = classificationRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("Not Found!"));
        
        return EntityClassificationModel.map(classification);
    }
    
}
