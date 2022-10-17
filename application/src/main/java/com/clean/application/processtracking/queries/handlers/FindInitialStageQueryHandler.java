package com.clean.application.processtracking.queries.handlers;

import com.clean.application.processtracking.queries.FindInitialStageQuery;
import com.clean.common.constant.StageTypeMapped;
import com.clean.common.exceptions.BussinessRuleException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.Connection;
import com.clean.domain.entity.prc.Stage;
import com.clean.domain.entity.prc.WorkFlow;
import com.clean.persistence.processtracking.ConnectionRepository;
import com.clean.persistence.processtracking.StageRepository;
import com.clean.persistence.processtracking.WorkFlowRepository;

import java.util.List;

public class FindInitialStageQueryHandler implements IRequestHandler<FindInitialStageQuery,Stage> {
    private WorkFlowRepository workFlowRepository;
    private StageRepository stageRepository;

    public FindInitialStageQueryHandler(WorkFlowRepository workFlowRepository, StageRepository stageRepository,ConnectionRepository connectionRepository) {
        this.workFlowRepository = workFlowRepository;
        this.stageRepository = stageRepository;
    }

    @Override
    public Stage handle(FindInitialStageQuery request) {
        WorkFlow workFlow = workFlowRepository.findByEntityIdAndClassificationId(request.getEntityId(),request.getClassificationId())
                .orElseThrow(() -> new BussinessRuleException("Work Flow Does Not Exists!"));

        Stage initialStage = stageRepository.findByWorkFlowIdAndStageTypeId(workFlow.getId(), StageTypeMapped.INITIAL)
                .orElseThrow(() -> new BussinessRuleException("Initial Stage Does Not Exists!"));

        return initialStage;
    }
}
