package com.clean.application.processtracking.queries.handlers;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.processtracking.models.StageModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchStageQueryHandler implements IRequestHandler<SearchStageQuery,List<StageModel>> {
    private EntityManager manager;
    @Autowired
    public SearchStageQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<StageModel> handle(SearchStageQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("Stage", "S")
        .innerJoin("StageType","T", "S.stageTypeId", "T.id")
        .innerJoin("WorkFlow", "W", "S.workFlowId", "W.id");

        if(request.getName() != null && !request.getName().isEmpty()){
            engine.where(WhereConditionType.NONE,"S.name", ConditionType.LIKE_ANY_POSITION, request.getName());
        }
        engine.addSelect("S.id").addSelect("S.name").addSelect("S.stageTypeId").addSelect("S.workFlowId")
        .addSelect("S.isActive").addSelect("S.isEditable")
        .addSelect("T.name").addSelect("W.name");

        return engine.execute(StageModel.class);
    }
    
}
