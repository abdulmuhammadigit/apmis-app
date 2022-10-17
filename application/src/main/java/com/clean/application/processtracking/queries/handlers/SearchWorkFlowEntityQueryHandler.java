package com.clean.application.processtracking.queries.handlers;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.processtracking.models.WorkFlowEntityModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchWorkFlowEntityQueryHandler implements IRequestHandler<SearchWorkFlowEntityQuery,List<WorkFlowEntityModel>> {
    EntityManager manager;
    @Autowired
    public SearchWorkFlowEntityQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<WorkFlowEntityModel> handle(SearchWorkFlowEntityQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("WorkFlowEntity", "E").addSelect("E.id").addSelect("E.description");
        if(request.getName() != null && !request.getName().isEmpty()){
            engine.where(WhereConditionType.NONE, "E.code", ConditionType.LIKE_ANY_POSITION, request.getName());
        }

        return engine.execute(WorkFlowEntityModel.class);
    }

    
}
