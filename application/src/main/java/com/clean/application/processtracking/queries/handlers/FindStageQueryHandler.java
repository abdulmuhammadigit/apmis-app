package com.clean.application.processtracking.queries.handlers;

import com.clean.application.processtracking.models.StageModel;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.Stage;
import com.clean.persistence.processtracking.StageRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class FindStageQueryHandler implements IRequestHandler<FindStageQuery,StageModel> {
    private StageRepository stageRepository;
    @Autowired
    public FindStageQueryHandler(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }
    @Override
    public StageModel handle(FindStageQuery request) {
        Stage stage = stageRepository.findById(request.getId()).orElseThrow(()->new EntityNotFoundException("Stage"));

        return StageModel.map(stage);
    }
    
}
