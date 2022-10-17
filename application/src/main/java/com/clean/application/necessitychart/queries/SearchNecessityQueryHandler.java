package com.clean.application.necessitychart.queries;

import com.clean.application.necessitychart.models.NecessityChartSearchModel;
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

public class SearchNecessityQueryHandler implements IRequestHandler<SearchNecessityChartQuery, List<NecessityChartSearchModel>> {
    EntityManager manager;
    UserService userService;
    UserStageRepository userStageRepository;
    @Autowired
    SearchNecessityQueryHandler(EntityManager manager, UserService userService, UserStageRepository userStageRepository) {
        this.manager = manager;
        this.userService = userService;
        this.userStageRepository = userStageRepository;
    }

    @Override
    public List<NecessityChartSearchModel> handle(SearchNecessityChartQuery request) {

        QueryEngine queryEngine = new QueryEngine(manager);
        queryEngine.from("NecessityChart", "N");
        queryEngine.innerJoin("FiscalYear", "F", "N.fiscalYearId", "F.id")
                .innerJoin("OrgUnit", "O", "N.orgUnitId", "O.id")
        ;

        if (request.getCode() != null && !request.getCode().isEmpty()) {
            queryEngine.where(WhereConditionType.NONE, "N.code", ConditionType.EQUAL, request.getCode());
        }

        if (request.getOrgUnitId() != 0) {
            queryEngine.where(WhereConditionType.AND, "N.orgUnitId", ConditionType.EQUAL, request.getOrgUnitId());
        }

        if (request.getFiscalYearId() != 0) {
            queryEngine.where(WhereConditionType.AND, "N.fiscalYearId", ConditionType.EQUAL, request.getFiscalYearId());
        }
        if(request.isAllocatedSearch()){
            queryEngine.where(WhereConditionType.AND, "N.stageId", ConditionType.EQUAL, StageMapped.NSC_APPROVE);
        }else{
            List<Integer> stageList= userStageRepository.findAllByUserIdAndEntityId(userService.getUserId(), EntityIdMapped.NecessityChart).stream().map(e->e.getStageId()).collect(Collectors.toList());
            queryEngine.where(WhereConditionType.AND, "N.stageId", ConditionType.IN, stageList);
        }

        queryEngine
                .addSelect("N.id") 
                .addSelect("N.code")
                .addSelect("N.orgUnitId")
                .addSelect("N.fiscalYearId")
                .addSelect("O.name")
                .addSelect("F.shamsiYear")
        ;
        return queryEngine.execute(NecessityChartSearchModel.class);
    }
}
