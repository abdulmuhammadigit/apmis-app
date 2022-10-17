package com.clean.application.processtracking.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.processtracking.models.StageModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.processtracking.StageRepository;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class GetStageQueryHandler implements IRequestHandler<GetStageQuery,List<StageModel>> {
    private StageRepository stageRepository;
    @Autowired
    public GetStageQueryHandler(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }
    @Override
    public List<StageModel> handle(GetStageQuery request) {
        if(request.getActiveOnly() == null || !request.getActiveOnly()){
            return stageRepository.getStageDetailList().stream().map(e->{
                StageModel model = StageModel.map(e);
                model.setStageTypeText(e.getStageType().getName());
                model.setWorkFlowText(e.getWorkFlow().getName());
                return model;
            }).collect(Collectors.toList());
        }
        else {
            return stageRepository.getActiveStageDetailList(true).stream().map(e->{
                StageModel model = StageModel.map(e);
                model.setStageTypeText(e.getStageType().getName());
                model.setWorkFlowText(e.getWorkFlow().getName());
                return model;
            }).collect(Collectors.toList());
        }
    }
    
}
