package com.clean.application.configuration.queries;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.configuration.models.UnitExchangeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchUnitExchangeQueryHandler implements IRequestHandler<SearchUnitExchangeQuery,List<UnitExchangeModel>> {
    private EntityManager manager;
    @Autowired
    public SearchUnitExchangeQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<UnitExchangeModel> handle(SearchUnitExchangeQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("UnitExchange", "U").
        innerJoin("Unit", "UN", "U.unitId", "UN.id"). 
        innerJoin("Unit", "T", "U.toUnitId", "T.id").
        leftJoin("Item", "I", "U.itemId", "I.id");
        if(request.getToUnitId() != 0){
            engine.where(WhereConditionType.NONE, "U.toUnitId", ConditionType.EQUAL, request.getToUnitId());
        }
        engine.addSelect("U.id").addSelect("U.unitId").addSelect("U.quantity").addSelect("U.toUnitId").addSelect("U.itemId").addSelect("UN.name").addSelect("T.name").addSelect("I.name");
        return engine.execute(UnitExchangeModel.class);
    }
    
}
