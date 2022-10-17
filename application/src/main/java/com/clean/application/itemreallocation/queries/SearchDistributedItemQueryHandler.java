package com.clean.application.itemreallocation.queries;

import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.itemreallocation.models.ItemDistributedSpecification;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchDistributedItemQueryHandler implements IRequestHandler<SearchDistributedItemQuery , List<ItemDistributedSpecification>> {
    private EntityManager manager;
    @Autowired
    public SearchDistributedItemQueryHandler(EntityManager entityManager) {
        this.manager = entityManager;
    }
    @Override
    public List<ItemDistributedSpecification> handle(SearchDistributedItemQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        
                engine.from("ItemDistributionDetail", "IDD")
                                .innerJoin("ItemDistributed", "IDS", "IDD.id", "IDS.itemDistributionDetailId")
                                .innerJoin("ItemDistributedSpecification", "DS", "IDS.id", "DS.itemDistributedId")
                                .innerJoin("ItemReceiptDetail", "D", "IDS.itemReceiptDetailId", "D.id")
                                .innerJoin("ItemReceipt", "R", "D.itemReceiptId", "R.id")
                                .innerJoin("ItemReceiptType", "IR", "R.itemReceiptTypeId", "IR.id")
                                .innerJoin("StockKeeper", "K", "R.stockKeeperId", "K.id")
                                .innerJoin("Employee", "E", "K.employeeId", "E.id")
                                .innerJoin("Unit", "U", "D.unitId", "U.id")
                                .innerJoin("ItemDetail", "ID", "D.itemDetailId", "ID.id")
                                .innerJoin("Item", "I", "ID.itemId", "I.id")
                                .innerJoin("ItemSpecification", "S", "DS.itemSpecificationId", "S.id")
                                .leftJoin("ItemReallocationSpecification", "IRS", "DS.id",
                                                "IRS.itemDistributedSpecificationId")
                                .leftJoin("Employee", "ES", "IRS.toEmployeeId", "ES.id");

                engine.addSelect("IRS.id")
                .addSelect("R.code").addSelect("R.receiveDate").addSelect("E.name")
                                .addSelect("E.lastName").addSelect("E.fatherName").addSelect("IR.name")
                                .addSelect("I.name").addSelect("ID.detail").addSelect("U.name").addSelect("S.price")
                                .addSelect("S.id").addSelect("S.serialNumber").addSelect("S.tagNumber")
                                .addSelect("S.location").addSelect("S.expirationDate").addSelect("DS.id")
                                .addSelect("IRS.toEmployeeId").addSelect("ES.name").addSelect("IRS.statusId");
                                
                engine.where(WhereConditionType.NONE, "IDD.id", ConditionType.EQUAL,
                                request.getItemDistributionDetailId());
                engine.where(WhereConditionType.AND, "I.consumable", ConditionType.EQUAL, false);
                List<ItemDistributedSpecification> nonConsumableList = engine
                                .execute(ItemDistributedSpecification.class);
                
                return nonConsumableList;
    }
    
}
