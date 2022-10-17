package com.clean.application.itemdistribution.quries;

import com.clean.application.itemdistribution.models.ItemDistributionSearchModel;
import com.clean.application.services.UserService;
import com.clean.common.constant.EntityIdMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.sec.UserStageRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class SearchItemDistributionQueryHandler implements IRequestHandler<SearchItemDistributionQuery, List<ItemDistributionSearchModel>> {

    private EntityManager manager;
    private UserService userService;
    private UserStageRepository userStageRepository;
    @Autowired
    public SearchItemDistributionQueryHandler(EntityManager manager, UserService userService, UserStageRepository userStageRepository) {
        this.manager = manager;
        this.userService = userService;
        this.userStageRepository = userStageRepository;
    }

    @Override
    public List<ItemDistributionSearchModel> handle(SearchItemDistributionQuery request) {

        QueryEngine queryEngine = new QueryEngine(manager);
        queryEngine.from("ItemDistribution", "FS")
                .innerJoin("ItemRequest", "FS9", "FS.itemRequestId", "FS9.id");

        if (request.getCode() != null && !request.getCode().isEmpty()) {
            queryEngine.where(WhereConditionType.NONE, "FS.code", ConditionType.EQUAL, request.getCode());
        }

        if (request.getDocumentNumber() != null && !request.getDocumentNumber().isEmpty()) {
            queryEngine.where(WhereConditionType.AND, "FS.documentNumber", ConditionType.EQUAL, request.getDocumentNumber());
        }

        List<Integer> stageList= userStageRepository.findAllByUserIdAndEntityId(userService.getUserId(), EntityIdMapped.ItemDistribution).stream().map(e->e.getStageId()).collect(Collectors.toList());
        queryEngine.where(WhereConditionType.AND, "FS.stageId", ConditionType.IN, stageList);

        queryEngine.addSelect("FS.id")
                .addSelect("FS.code")
                .addSelect("FS.documentNumber")
                .addSelect("FS.date")
                .addSelect("FS.itemRequestId")
                .addSelect("FS9.description")
                .addSelect("FS9.code");
        return queryEngine.execute(ItemDistributionSearchModel.class);
    }
}
