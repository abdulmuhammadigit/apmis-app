package com.clean.application.item.queries;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;


import javax.persistence.Convert;
import javax.persistence.EntityManager;
import java.util.List;

public class SearchItemDetailQueryHandler implements IRequestHandler<SearchItemDetailQuery, List<ItemDetailModel>> {
    private EntityManager manager;
  //  private ItemRepository itemRepository;

    SearchItemDetailQueryHandler(ItemDetailRepository reposity, EntityManager manager) {
        this.manager = manager;
       // this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDetailModel> handle(SearchItemDetailQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        // get item list
        engine.from("ItemDetail", "ID").distinct(true)
                .innerJoin("Item", "I", "ID.itemId", "I.id").innerJoin("Category", "C", "I.categoryId", "C.id")
                .innerJoin("ItemAttributeValue", "AV", "ID.id", "AV.itemDetailId")
                .innerJoin("Unit", "U", "I.unitId", "U.id");


        engine.addSelect("ID.id")
        .addSelect("I.name")
        .addSelect("ID.detail")
        .addSelect("ID.code")
        .addSelect("C.name")
        .addSelect("I.consumable")
        .addSelect("ID.itemId")
        .addSelect("U.name")

        ;
        if (request.getCategoryId() != 0)
            engine.where(WhereConditionType.NONE, "C.id", ConditionType.EQUAL, request.getCategoryId());
        if (request.getItemId() != 0)
            engine.where(WhereConditionType.AND, "I.id", ConditionType.EQUAL, request.getItemId());
        if(request.getDetail() != null && !request.getDetail().isEmpty()){
            engine.where(WhereConditionType.AND, "ID.detail", ConditionType.LIKE_ANY_POSITION, request.getDetail());
        }
        return engine.execute(ItemDetailModel.class);
    }
}
