package com.clean.application.processtracking.commands.handlers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.clean.application.itemdistribution.commands.ProcessItemDistributionCheck;
import com.clean.application.itemreallocation.commands.ProcessItemReallocationCheckCommand;
import com.clean.application.itemreceipt.commands.ProcessItemReceipCheck;
import com.clean.application.itemrequest.commands.ProcessItemRequestCheckCommand;
import com.clean.application.necessitychart.commands.ProcessNecessityChartCheckCommand;
import com.clean.application.processtracking.commands.CreateHistoryCommand;
import com.clean.application.processtracking.models.HistoryModel;
import com.clean.application.processtracking.queries.SearchHistoryQuery;
import com.clean.application.services.UserService;
import com.clean.common.constant.BusinessRuleMapped;
import com.clean.common.constant.EntityMapped;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.prc.Connection;
import com.clean.domain.entity.prc.History;
import com.clean.persistence.processtracking.ConnectionRepository;
import com.clean.persistence.processtracking.HistoryRepository;
import com.clean.persistence.processtracking.WorkFlowEntityRepository;
import com.clean.persistence.processtracking.WorkFlowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreateHistoryCommandHandler implements IRequestHandler<CreateHistoryCommand, List<HistoryModel>> {
    private HistoryRepository historyRepository;
    private ConnectionRepository connectionRepository;
    private WorkFlowEntityRepository entityRepository;
    private WorkFlowRepository workFlowRepository;
    private UserService userService;
    private IMediator mediator;

    @Autowired
    public CreateHistoryCommandHandler(
            HistoryRepository historyRepository,
            ConnectionRepository connectionRepository,
            WorkFlowEntityRepository entityRepository,
            WorkFlowRepository workFlowRepository,
            IMediator mediator,
            UserService userService
    ) {
        this.historyRepository = historyRepository;
        this.connectionRepository = connectionRepository;
        this.entityRepository = entityRepository;
        this.workFlowRepository = workFlowRepository;
        this.mediator = mediator;
        this.userService = userService;
    }

    @Override
    public List<HistoryModel> handle(CreateHistoryCommand request) {

        // ------------------ check up
        //1. check for already next;
        if (request.getCurrentStageId() == request.getToStageId()) {
            throw new RuntimeException("اسناد طی مراحل گردیده است!");
        }

        // ----------------- find neccesary const section
        //1. find businessRule
        Connection connection = connectionRepository.findByStageAndToStage(request.getCurrentStageId(), request.getToStageId());
        String businessRule = connection.getBusinessRule().getName();

        //2. find entity
        int entityId = entityRepository.findByCode(request.getEntity()).getId();

        //3. find workflow 
        int workFlowId = 0;
        if (request.getClassificationId() != 0) {
            workFlowId = workFlowRepository.findByEntityIdAndClassificationId(entityId, request.getClassificationId())
                .orElseThrow(() -> new EntityNotFoundException("Work Flow")).getId();
        } else {
            workFlowId = workFlowRepository.findByEntityId(entityId).getId();
        }

        //---------------- business rule implementation section
        if(request.getEntity().equals(EntityMapped.NecessityChart)){ 
            if (businessRule.equals(BusinessRuleMapped.NecessityChartDetailCheck)) {
                mediator.send(ProcessNecessityChartCheckCommand.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule(BusinessRuleMapped.NecessityChartDetailCheck)
                        .build()
                );
            }
            else if (businessRule.equals(BusinessRuleMapped.NecessityChartCommissionDecision)) {
                mediator.send(ProcessNecessityChartCheckCommand.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule(BusinessRuleMapped.NecessityChartCommissionDecision)
                        .build()
                );
            }else{
                mediator.send(ProcessNecessityChartCheckCommand.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule("")
                        .build()
                );
            }
        }
        else if(request.getEntity().equals(EntityMapped.ItemRequest)){
            if (businessRule.equals(BusinessRuleMapped.ItemRequestDetailCheck)) {
                mediator.send(ProcessItemRequestCheckCommand.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule(BusinessRuleMapped.ItemRequestDetailCheck)
                        .build()
                );
            }
            else{
                mediator.send(ProcessItemRequestCheckCommand.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule("")
                        .build()
                );
            }
        }
        else if(request.getEntity().equals(EntityMapped.ItemReceipt)){
            if (businessRule.equals(BusinessRuleMapped.ItemReceiptDetailCheck)) {
                mediator.send(ProcessItemReceipCheck.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule(BusinessRuleMapped.ItemReceiptDetailCheck)
                        .build()
                );
            }
            else if (businessRule.equals(BusinessRuleMapped.ItemReceiptApprove)) {
                mediator.send(ProcessItemReceipCheck.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule(BusinessRuleMapped.ItemReceiptApprove)
                        .build()
                );
            }
            else{
                mediator.send(ProcessItemReceipCheck.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule("")
                        .build()
                );
            }
        }
        else if(request.getEntity().equals(EntityMapped.ItemDistribution)){
            if (businessRule.equals(BusinessRuleMapped.ItemDistributionDetailCheck)) {
                mediator.send(ProcessItemDistributionCheck.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule(BusinessRuleMapped.ItemDistributionDetailCheck)
                        .build()
                );
            }
            else{
                mediator.send(ProcessItemDistributionCheck.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule("")
                        .build()
                );
            }
        }else if(request.getEntity().equals(EntityMapped.ItemReallocation)){
            if (businessRule.equals(BusinessRuleMapped.ReallocationApprove)) {
                mediator.send(ProcessItemReallocationCheckCommand.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule(BusinessRuleMapped.ReallocationApprove)
                        .build()
                );
            }
            else if (businessRule.equals(BusinessRuleMapped.ReallocationPrice)) {
                mediator.send(ProcessItemReallocationCheckCommand.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule(BusinessRuleMapped.ReallocationPrice)
                        .build()
                );
            }
            else{
                mediator.send(ProcessItemReallocationCheckCommand.builder()
                        .recordId(request.getRecordId())
                        .stageId(request.getToStageId())
                        .businessRule("")
                        .build()
                );
            }
        }

        // add processed record history
        History history = new History();

        history.setEntityId(entityId);
        history.setRecordId(request.getRecordId());
        history.setWorkFlowId(workFlowId);
        history.setDate(Timestamp.from(Instant.now()));
        history.setDescription(request.getDescription());
        history.setUserId(userService.getUserId());
        history.setToStageId(connection.getToStageId());
        history.setFromStageId(connection.getStageId());
        history = historyRepository.save(history);

        return mediator.send(new SearchHistoryQuery(request.getEntity(), request.getClassificationId(), request.getRecordId()));
    }

}
