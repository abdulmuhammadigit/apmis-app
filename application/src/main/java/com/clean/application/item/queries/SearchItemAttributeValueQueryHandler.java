package com.clean.application.item.queries;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
@Service
public class SearchItemAttributeValueQueryHandler implements IRequestHandler<SearchItemAttributeValueQuery,List<ItemDetailModel>> {
    private EntityManager manager;
    @Autowired
    SearchItemAttributeValueQueryHandler(EntityManager manager){
        this.manager = manager;
    }
    @Override
    public List<ItemDetailModel> handle(SearchItemAttributeValueQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("ItemDetail","ID")
                .innerJoin("Item","I","ID.itemId","I.id")
                .innerJoin("Category","C","I.categoryId","C.id")
                .innerJoin("Unit", "U", "I.unitId", "U.id");
        engine.addSelect("ID.id")
                .addSelect("I.name")
                .addSelect("ID.detail")
                .addSelect("ID.code")
                .addSelect("C.name")
                .addSelect("I.consumable")
                .addSelect("I.id")
                .addSelect("U.name");
        engine.where(WhereConditionType.NONE,"I.id", ConditionType.EQUAL,request.getItemId());
        return engine.execute(ItemDetailModel.class);
    }
}
