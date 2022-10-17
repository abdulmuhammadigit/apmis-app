package com.clean.application.processtracking.commands.handlers;

import com.clean.application.processtracking.commands.CreateStageCommand;
import com.clean.application.processtracking.models.StageModel;
import com.clean.common.exceptions.BussinessRuleException;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.Stage;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateStageCommandHandler implements IRequestHandler<CreateStageCommand,StageModel> {
    private StageRepository stageRepository;
    @Autowired
    public CreateStageCommandHandler(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }
    @Override
    public StageModel handle(CreateStageCommand request) {
        if(stageRepository.existsByNameAndStageTypeIdAndWorkFlowIdAndIdIsNot(request.getName(), request.getStageTypeId(),request.getWorkFlowId(),(request.getId() == null ? 0 :request.getId()))) {
            throw new RuntimeException("Stage Exist!");
        }

        Stage stage = null;
        if(request.getId() != null){
            stage = stageRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Stage",request.getId()));

            if(!stage.isActive()){
                throw new BussinessRuleException("This Stage Is In Active!");
            }
        }
        else {
            stage = new Stage();
        }
        stage.setName(request.getName());
        stage.setStageTypeId(request.getStageTypeId());
        stage.setWorkFlowId(request.getWorkFlowId());
        stage.setActive(request.getIsActive());
        stage.setEditable(request.getIsEditable());
        stage = stageRepository.save(stage);
        return StageModel.map(stage);
    }
    
}
