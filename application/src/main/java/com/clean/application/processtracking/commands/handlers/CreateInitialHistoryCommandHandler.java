package com.clean.application.processtracking.commands.handlers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.clean.application.processtracking.commands.CreateInitialHistoryCommand;
import com.clean.application.processtracking.models.InitialHistoryCommandResult;
import com.clean.application.services.UserService;
import com.clean.common.constant.StageTypeMapped;
import com.clean.common.exceptions.BussinessRuleException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.Connection;
import com.clean.domain.entity.prc.History;
import com.clean.domain.entity.prc.Stage;
import com.clean.domain.entity.prc.WorkFlow;
import com.clean.persistence.processtracking.ConnectionRepository;
import com.clean.persistence.processtracking.HistoryRepository;
import com.clean.persistence.processtracking.StageRepository;
import com.clean.persistence.processtracking.WorkFlowRepository;
import org.springframework.transaction.annotation.Transactional;


public class CreateInitialHistoryCommandHandler implements IRequestHandler<CreateInitialHistoryCommand,InitialHistoryCommandResult > {
    private ConnectionRepository connectionRepository;
    private WorkFlowRepository workFlowRepository;
    private StageRepository stageRepository;
    private HistoryRepository historyRepository;
    private UserService userService;

    public CreateInitialHistoryCommandHandler(
        ConnectionRepository connectionRepository,
        WorkFlowRepository workFlowRepository,
        StageRepository stageRepository,
        HistoryRepository historyRepository,
        UserService userService
    ) {
        this.connectionRepository = connectionRepository;
        this.workFlowRepository = workFlowRepository;
        this.stageRepository = stageRepository;
        this.historyRepository = historyRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public InitialHistoryCommandResult handle(CreateInitialHistoryCommand request) {
        WorkFlow workFlow = new WorkFlow();
        if(request.getClassificationId() != null && request.getClassificationId() != 0){
            workFlow= workFlowRepository.findByEntityIdAndClassificationId(request.getEntityId(),request.getClassificationId())
            .orElseThrow(() -> new BussinessRuleException("Work Flow Does Not Exists!"));
        }else{
            workFlow= workFlowRepository.findByEntityId(request.getEntityId());
            if(workFlow == null){
                throw new BussinessRuleException("Work Flow Does Not Exists!");
            }
        }
         
        
        Stage initialStage = stageRepository.findByWorkFlowIdAndStageTypeId(workFlow.getId(), StageTypeMapped.INITIAL)
            .orElseThrow(() -> new BussinessRuleException("Initial Stage Does Not Exists!"));

        List<Connection> connections = connectionRepository.findAllByStageId(initialStage.getId());
        if(connections.size() != 1){
            throw new BussinessRuleException("Initial Stage Has Invalid Number Of Connections!");
        }
        Connection connection = connections.get(0);

        History history = new History();
        history.setEntityId(request.getEntityId());
        history.setRecordId(request.getRecordId());
        history.setWorkFlowId(workFlow.getId());
        history.setDate(Timestamp.from(Instant.now()));
        history.setDescription("");
        history.setUserId(userService.getUserId());
        history.setToStageId(connection.getToStageId());
        history.setFromStageId(connection.getStageId());

        history = historyRepository.save(history);

        InitialHistoryCommandResult result = new InitialHistoryCommandResult();
        result.setHistory(history);
        result.setConnection(connection);
        result.setInitial(initialStage);
        result.setToStage(connection.getToStage());

        return result;
    }
    
}
