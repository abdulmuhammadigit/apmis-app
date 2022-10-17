package com.clean.application.processtracking.queries.handlers;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.processtracking.models.EntityClassificationModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.WhereConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchEntityClassificationQueryHandler implements IRequestHandler<SearchEntityClassificationQuery,List<EntityClassificationModel>> {
    private EntityManager manager;
    @Autowired
    public SearchEntityClassificationQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<EntityClassificationModel> handle(SearchEntityClassificationQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("EntityClassification", "C")
        .innerJoin("WorkFlowEntity", "E", "C.entityId", "E.id");
        if(request.getName() != null && !request.getName().isEmpty()){
            engine.where(WhereConditionType.NONE, "C.name", ConditionType.LIKE_ANY_POSITION, request.getName());
        }
        engine.addSelect("C.id").addSelect("C.name").addSelect("C.entityId").addSelect("E.description");

        return engine.execute(EntityClassificationModel.class);
    }
    
}
