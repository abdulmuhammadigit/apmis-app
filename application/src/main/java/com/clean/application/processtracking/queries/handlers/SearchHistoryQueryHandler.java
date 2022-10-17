package com.clean.application.processtracking.queries.handlers;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.processtracking.models.HistoryModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchHistoryQueryHandler implements IRequestHandler<SearchHistoryQuery,List<HistoryModel>> {
    EntityManager manager;
    @Autowired
    public SearchHistoryQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<HistoryModel> handle(SearchHistoryQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("History", "H")
        .innerJoin("Stage", "S", "H.fromStageId", "S.id")
        .innerJoin("Stage", "T", "H.toStageId", "T.id")
        .innerJoin("WorkFlowEntity", "E", "H.entityId", "E.id")
        .innerJoin("WorkFlow", "W", "H.workFlowId", "W.id");

        engine.where(WhereConditionType.NONE, "H.recordId", ConditionType.EQUAL, request.getRecordId());
        engine.where(WhereConditionType.AND, "E.code", ConditionType.EQUAL, request.getEntity());

        if(request.getClassificationId() != 0){
            engine.where(WhereConditionType.AND, "W.classificationId", ConditionType.EQUAL, request.getClassificationId());
        }
        engine.addSelect("H.id")
        .addSelect("H.entityId")
        .addSelect("H.recordId")
        .addSelect("H.workFlowId")
        .addSelect("H.date")
        .addSelect("H.description")
        .addSelect("H.toStageId")
        .addSelect("H.fromStageId")
        .addSelect("S.name")
        .addSelect("T.name")
        .addSelect("E.description")
        .addSelect("W.name");
        return engine.execute(HistoryModel.class);
    }
    
}
