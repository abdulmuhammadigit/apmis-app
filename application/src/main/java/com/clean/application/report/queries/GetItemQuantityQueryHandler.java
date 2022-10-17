package com.clean.application.report.queries;

import com.clean.application.report.models.ItemQuantityModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class GetItemQuantityQueryHandler implements IRequestHandler<GetItemQuantityQuery, List<ItemQuantityModel>> {

    private EntityManager manager;
    private ItemReceiptDetailRepository itemReceiptDetailRepository;
    @Autowired
    GetItemQuantityQueryHandler( EntityManager manager, ItemReceiptDetailRepository itemReceiptDetailRepository) {
        this.manager = manager;
        this.itemReceiptDetailRepository = itemReceiptDetailRepository;
    }

    @Override
    public List<ItemQuantityModel> handle(GetItemQuantityQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("Item", "I");
        List<ItemQuantityModel> list = new ArrayList<>();

        if (request.getCategoryId() != 0) {
            engine.where(WhereConditionType.NONE, "I.categoryId", ConditionType.EQUAL, request.getCategoryId());
        }

        if (request.getDetailId() != 0) {
            engine.innerJoin("ItemDetail", "ID", "I.id", "ID.itemId");
            engine.addSelect("ID.id")
                    .addSelect("ID.detail")
                    .addSelect("0");

            if (request.getDetailId() != 0) {
                engine.where(WhereConditionType.AND, "ID.id", ConditionType.EQUAL, request.getDetailId());
            }
            list = engine.execute(ItemQuantityModel.class);

            list.forEach(e -> {
                Integer countof = itemReceiptDetailRepository.countOfItemDetail(e.getId());
                int count = (countof == null || countof == 0 ? 0 : countof);
                e.setCount(count);
            });
        }
        else {
            engine.addSelect("I.id")
                    .addSelect("I.name")
                    .addSelect("0");
            if (request.getItemId() != 0) {
                engine.where(WhereConditionType.AND, "I.id", ConditionType.EQUAL, request.getItemId());
            }

            list = engine.execute(ItemQuantityModel.class);
            list.forEach(e -> {
                Integer countof = itemReceiptDetailRepository.countOfItem(e.getId());
                int count = (countof == null || countof == 0 ? 0 : countof);
                e.setCount(count);
            });
        }

        return  list;

    }


}
