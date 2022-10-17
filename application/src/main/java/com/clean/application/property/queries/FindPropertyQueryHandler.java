package com.clean.application.property.queries;

import com.clean.application.itemdistribution.models.ItemReceiptDistributedSpeceficationModel;
import com.clean.application.property.models.PropertyModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class FindPropertyQueryHandler implements IRequestHandler<FindPropertyQuery, List<PropertyModel>> {
    private EntityManager manager;

    @Autowired
    FindPropertyQueryHandler(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<PropertyModel> handle(FindPropertyQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("ItemDistributionDetail", "IDD")
                .innerJoin("ItemDistributed", "IDS", "IDD.id", "IDS.itemDistributionDetailId")
                .innerJoin("ItemDistributedSpecification", "DS", "IDS.id", "DS.itemDistributedId")
                .innerJoin("ItemReceiptDetail", "D", "IDS.itemReceiptDetailId", "D.id")
                .innerJoin("ItemReceipt", "R", "D.itemReceiptId", "R.id")
                .innerJoin("ItemDistribution", "IDSS", "IDD.itemDistributionId","IDSS.id")
                .innerJoin("ItemRequest","IRQ","IDSS.itemRequestId","IRQ.id")
                .innerJoin("ItemReceiptType", "IR", "R.itemReceiptTypeId", "IR.id")
                .innerJoin("Employee", "E", "IRQ.employeeId", "E.id")
                .innerJoin("ItemDetail", "ID", "D.itemDetailId", "ID.id")
                .innerJoin("Item", "I", "ID.itemId", "I.id")
                .innerJoin("ItemSpecification", "S", "DS.itemSpecificationId", "S.id");

        engine
                .addSelect("E.code")
                .addSelect("E.name")
                .addSelect("ID.detail")
                .addSelect("ID.code")
                .addSelect("R.code")
                .addSelect("IDSS.date")
                .addSelect("S.price")
                .addSelect("S.tagNumber")
                .addSelect("S.expirationDate")
                .addSelect("S.serialNumber");
        engine.where(WhereConditionType.NONE, "IRQ.employeeId", ConditionType.EQUAL,
                request.getEmployeeId());
        engine.where(WhereConditionType.AND, "DS.id", ConditionType.EQUAL,
                request.getItemDistributedSpecificationId());
        engine.where(WhereConditionType.AND, "I.consumable", ConditionType.EQUAL, false);
        List<PropertyModel> nonConsumableList = engine
                .execute(PropertyModel.class);
        return  nonConsumableList;
    }

}
