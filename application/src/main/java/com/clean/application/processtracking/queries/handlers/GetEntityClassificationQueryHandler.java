package com.clean.application.processtracking.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.processtracking.models.EntityClassificationModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.processtracking.EntityClassificationRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class GetEntityClassificationQueryHandler implements IRequestHandler<GetEntitClassificationQuery, List<EntityClassificationModel>> {
    private EntityClassificationRepository classificationRepository;
    @Autowired
    public GetEntityClassificationQueryHandler(EntityClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }
    @Override
    public List<EntityClassificationModel> handle(GetEntitClassificationQuery request) {
        return classificationRepository.findAllByEntityId(request.getEntityId())
            .stream().map(EntityClassificationModel::map).collect(Collectors.toList());
    }
    
}
