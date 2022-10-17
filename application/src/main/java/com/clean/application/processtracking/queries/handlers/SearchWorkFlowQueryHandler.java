package com.clean.application.processtracking.queries.handlers;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.processtracking.models.WorkFlowModel;
import com.clean.application.processtracking.queries.*;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchWorkFlowQueryHandler implements IRequestHandler<SearchWorkFlowQuery,List<WorkFlowModel>> {
    private EntityManager manager;
    @Autowired
    public SearchWorkFlowQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<WorkFlowModel> handle(SearchWorkFlowQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("WorkFlow", "W")
        .innerJoin("WorkFlowEntity", "E", "W.entityId", "E.id")
        .leftJoin("EntityClassification", "C", "W.classificationId", "C.id")
        .leftJoin("WorkFlow", "P", "W.parentId", "P.id");

        if(request.getName() != null && !request.getName().isEmpty()){
            engine.where(WhereConditionType.NONE, "W.name", ConditionType.LIKE_ANY_POSITION, request.getName());
        }
        engine.addSelect("W.id").addSelect("W.name").addSelect("W.entityId").addSelect("W.classificationId")
        .addSelect("W.parentId").addSelect("E.description").addSelect("C.name").addSelect("P.name");
        return engine.execute(WorkFlowModel.class);
    }
    
}
