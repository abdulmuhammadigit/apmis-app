package com.clean.application.item.queries;

import com.clean.application.item.models.ItemAttributeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchItemAttributeQueryHandler implements IRequestHandler<SearchItemAttributeQuery, List<ItemAttributeModel>> {
    private EntityManager manager;
    @Autowired
    SearchItemAttributeQueryHandler(EntityManager manager){
        this.manager = manager;
    }
    @Override
    public List<ItemAttributeModel> handle(SearchItemAttributeQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("ItemAttribute","IA")
                .innerJoin("Item","I","IA.itemId","I.id")
                .innerJoin("Attribute","A","IA.attributeId","A.id");
        engine.addSelect("IA.id")
                .addSelect("IA.attributeId")
                .addSelect("IA.itemId")
                .addSelect("A.name")
                .addSelect("I.name");
        engine.where(WhereConditionType.NONE,"IA.itemId", ConditionType.EQUAL,request.getItemId());
        return engine.execute(ItemAttributeModel.class);
    }
}
