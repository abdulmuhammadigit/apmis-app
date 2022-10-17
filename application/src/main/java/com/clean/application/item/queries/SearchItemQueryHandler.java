package com.clean.application.item.queries;

import com.clean.application.item.models.ItemModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchItemQueryHandler implements IRequestHandler<SearchItemQuery,List<ItemModel>> {
    private EntityManager manager;
    @Autowired
    SearchItemQueryHandler(EntityManager manager){
        this.manager = manager;
    }
    @Override
    public List<ItemModel> handle(SearchItemQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("Item","I")
                .innerJoin("Category","C","I.categoryId","C.id")
                .innerJoin("Unit","U","I.unitId","U.id");
        engine.addSelect("I.id")
                .addSelect("I.name")
                .addSelect("I.consumable")
                .addSelect("I.categoryId")
                .addSelect("I.unitId")
                .addSelect("C.name")
                .addSelect("U.name");
        if(request.getCategoryId() != 0){
            engine.where(WhereConditionType.NONE,"I.categoryId", ConditionType.EQUAL,request.getCategoryId());
        }
        if(request.getName() != null && !request.getName().isEmpty()){
            engine.where(WhereConditionType.AND,"I.name",ConditionType.LIKE_ANY_POSITION,request.getName());
        }
        return engine.execute(ItemModel.class);
    }
}
