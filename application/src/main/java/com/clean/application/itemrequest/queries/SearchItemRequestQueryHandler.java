package com.clean.application.itemrequest.queries;

import com.clean.application.itemrequest.models.ItemRequesSearchModel;
import com.clean.application.services.UserService;
import com.clean.common.constant.EntityIdMapped;
import com.clean.common.constant.StageMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.sec.UserStageRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class SearchItemRequestQueryHandler implements IRequestHandler<SearchItemRequestQuery, List<ItemRequesSearchModel>> {

    private EntityManager manager;
    private UserService userService;
    private UserStageRepository userStageRepository;

    @Autowired
    SearchItemRequestQueryHandler(EntityManager manager, UserService userService , UserStageRepository userStageRepository){
        this.manager = manager;
        this.userService = userService;
        this.userStageRepository = userStageRepository;
    }

    @Override
    public List<ItemRequesSearchModel> handle(SearchItemRequestQuery request) {
        QueryEngine queryEngine = new QueryEngine(manager);
        queryEngine.from("ItemRequest", "IR")
                .innerJoin("Employee", "E", "IR.employeeId", "E.id");

        if (request.getCode() != null && !request.getCode().isEmpty()) {
            queryEngine.where(WhereConditionType.NONE, "IR.code", ConditionType.EQUAL, request.getCode());
        }

        if (request.getDocumentNumber() != null && !request.getDocumentNumber().isEmpty()) {
            queryEngine.where(WhereConditionType.AND, "IR.documentNumber", ConditionType.EQUAL, request.getDocumentNumber());
        }

        if(request.isDistributionSearch()){
            queryEngine.where(WhereConditionType.AND, "IR.stageId", ConditionType.EQUAL, StageMapped.FS9_DISTRIBUTION);
        }
        
        if(request.isDistributionSearch()){
            queryEngine.where(WhereConditionType.AND, "IR.stageId", ConditionType.EQUAL, StageMapped.FS9_DISTRIBUTION );
        }else{
            List<Integer> stageList= userStageRepository.findAllByUserIdAndEntityId(userService.getUserId(), EntityIdMapped.ItemRequest).stream().map(e->e.getStageId()).collect(Collectors.toList());
            queryEngine.where(WhereConditionType.AND, "IR.stageId", ConditionType.IN, stageList);
        }

        queryEngine
                .addSelect("IR.id")
                .addSelect("IR.code")
                .addSelect("IR.completed")
                .addSelect("IR.date")
                .addSelect("IR.description")
                .addSelect("IR.documentNumber")
                .addSelect("IR.employeeId")
                .addSelect("E.name")
                .addSelect("E.lastName")
                .addSelect("E.fatherName");
        return queryEngine.execute(ItemRequesSearchModel.class);
    }
}
