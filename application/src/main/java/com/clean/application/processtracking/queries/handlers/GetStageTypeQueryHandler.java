package com.clean.application.processtracking.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.processtracking.models.StageTypeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.processtracking.StageTypeRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class GetStageTypeQueryHandler implements IRequestHandler<GetStageTypeQuery,List<StageTypeModel>> {
    private StageTypeRepository stageTypeRepository;
    @Autowired
    public GetStageTypeQueryHandler(StageTypeRepository stageTypeRepository) {
        this.stageTypeRepository = stageTypeRepository;
    }
    @Override
    public List<StageTypeModel> handle(GetStageTypeQuery request) {
        return  stageTypeRepository.findAll().stream().map(type->{
            StageTypeModel model = new StageTypeModel();
            model.setId(type.getId());
            model.setName(type.getName());
            return model;
        }).collect(Collectors.toList());

    }
    
}
