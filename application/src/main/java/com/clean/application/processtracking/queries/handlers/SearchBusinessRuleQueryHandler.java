package com.clean.application.processtracking.queries.handlers;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.processtracking.models.BusinessRuleModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import com.clean.application.processtracking.queries.*;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchBusinessRuleQueryHandler implements IRequestHandler<SearchBusinessRuleQuery,List<BusinessRuleModel>> {
    private EntityManager manager;
    @Autowired
    public SearchBusinessRuleQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<BusinessRuleModel> handle(SearchBusinessRuleQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("BusinessRule", "B").addSelect("B.id").addSelect("B.name")
        .addSelect("B.entityId").addSelect("B.description").addSelect("E.description")
        .innerJoin("WorkFlowEntity", "E", "B.entityId", "E.id");
        
        if(request.getName() != null && !request.getName().isEmpty()){
            engine.where(WhereConditionType.NONE, "B.name", ConditionType.LIKE_ANY_POSITION, request.getName());
        }

        return engine.execute(BusinessRuleModel.class);
    }
    
}
