package com.clean.application.report.queries;

import com.clean.application.itemdistribution.models.ItemReceiptDistributedSpeceficationModel;
import com.clean.application.itemdistribution.quries.SearchDistributedSpecificationQuery;
import com.clean.application.report.models.EmployeesAllocatedItemsModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class GetEmployeesAllocatedItemsHandler implements IRequestHandler<GetEmployeesAllocatedItemsQuery, List<EmployeesAllocatedItemsModel>> {
    private EntityManager manager;

    @Autowired
    GetEmployeesAllocatedItemsHandler(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<EmployeesAllocatedItemsModel> handle(GetEmployeesAllocatedItemsQuery request) {
        QueryEngine engine = new QueryEngine(manager);

        engine.from("ItemDistributionDetail", "IDD")
                .innerJoin("ItemDistribution","IDN","IDD.itemDistributionId","IDN.id")
                .innerJoin("ItemRequest","IR","IDN.itemRequestId","IR.id")
                .innerJoin("ItemDistributed", "IDS", "IDD.id", "IDS.itemDistributionDetailId")
                .innerJoin("ItemDistributedSpecification", "DS", "IDS.id", "DS.itemDistributedId")
                .innerJoin("ItemDetail", "ID", "IDD.itemDetailId", "ID.id")
                .innerJoin("Item", "I", "ID.itemId", "I.id")
                .innerJoin("ItemSpecification", "S", "DS.itemSpecificationId", "S.id");

        engine.addSelect("IDS.id")
                .addSelect("I.name")
                .addSelect("ID.detail")
                .addSelect("S.serialNumber")
                .addSelect("S.tagNumber")
                .addSelect("IR.employeeId")
                .addSelect("DS.id")

        ;
        engine.where(WhereConditionType.NONE,"IR.employeeId", ConditionType.EQUAL,request.getEmployeeId());
        engine.where(WhereConditionType.AND, "I.consumable", ConditionType.EQUAL, false);
        List<EmployeesAllocatedItemsModel> nonConsumableList = engine.execute(EmployeesAllocatedItemsModel.class);

        return nonConsumableList;
    }


}
