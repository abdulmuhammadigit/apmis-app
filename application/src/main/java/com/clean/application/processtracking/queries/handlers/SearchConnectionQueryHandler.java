package com.clean.application.processtracking.queries.handlers;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.processtracking.models.ConnectionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchConnectionQueryHandler implements IRequestHandler<SearchConnectionQuery,List<ConnectionModel>> {
    private EntityManager manager;
    @Autowired
    public SearchConnectionQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<ConnectionModel> handle(SearchConnectionQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("Connection", "C")
        .innerJoin("Stage", "S", "C.stageId", "S.id")
        .innerJoin("Stage", "T", "C.toStageId", "T.id")
        .innerJoin("BusinessRule", "R", "C.businessRuleId", "R.id");

        if(request.getStageId() != 0){
            engine.where(WhereConditionType.NONE, "C.stageId", ConditionType.EQUAL, request.getStageId());
        }

        if(request.getToStageId() != 0){
            engine.where(WhereConditionType.AND, "C.toStageId", ConditionType.EQUAL, request.getToStageId());
        }

        if(request.getBusinessRuleId() != 0){
            engine.where(WhereConditionType.AND, "C.businessRuleId", ConditionType.EQUAL, request.getBusinessRuleId());
        }

        engine.addSelect("C.id").addSelect("C.stageId").addSelect("C.toStageId").addSelect("C.businessRuleId")
        .addSelect("S.name").addSelect("T.name").addSelect("R.name");
        return engine.execute(ConnectionModel.class);
    }
    
}
