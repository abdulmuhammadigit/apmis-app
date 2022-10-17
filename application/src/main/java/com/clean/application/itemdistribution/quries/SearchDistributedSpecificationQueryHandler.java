package com.clean.application.itemdistribution.quries;

import com.clean.application.itemdistribution.models.ItemReceiptDistributedModel;
import com.clean.application.itemdistribution.models.ItemReceiptDistributedSpeceficationModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchDistributedSpecificationQueryHandler implements
                IRequestHandler<SearchDistributedSpecificationQuery, List<ItemReceiptDistributedSpeceficationModel>> {
        private EntityManager manager;

        @Autowired
        SearchDistributedSpecificationQueryHandler(EntityManager manager) {
                this.manager = manager;
        }

        @Override
        public List<ItemReceiptDistributedSpeceficationModel> handle(SearchDistributedSpecificationQuery request) {
                QueryEngine engine = new QueryEngine(manager);
                engine.from("ItemReceipt", "R").innerJoin("ItemReceiptType", "IR", "R.itemReceiptTypeId", "IR.id")
                                .innerJoin("StockKeeper", "K", "R.stockKeeperId", "K.id")
                                .innerJoin("Employee", "E", "K.employeeId", "E.id")
                                .innerJoin("ItemReceiptDetail", "D", "R.id", "D.itemReceiptId")
                                .innerJoin("Unit", "U", "D.unitId", "U.id")
                                .innerJoin("ItemDetail", "ID", "D.itemDetailId", "ID.id")
                                .innerJoin("Item", "I", "ID.itemId", "I.id")
                                .innerJoin("ItemDistributed", "IDS", "D.id", "IDS.itemReceiptDetailId")
                                .innerJoin("ItemDistributionDetail", "IDD", "IDS.itemDistributionDetailId", "IDD.id");

                engine.addSelect("R.id").addSelect("R.code").addSelect("R.receiveDate").addSelect("E.name")
                                .addSelect("E.lastName").addSelect("E.fatherName").addSelect("IR.name")
                                .addSelect("I.name").addSelect("ID.detail").addSelect("U.name").addSelect("D.price");
                engine.where(WhereConditionType.NONE, "IDD.itemDistributionId", ConditionType.EQUAL,
                                request.getItemDistributionId());
                engine.where(WhereConditionType.AND, "I.consumable", ConditionType.EQUAL, true);
                List<ItemReceiptDistributedModel> consumableList = engine.execute(ItemReceiptDistributedModel.class);

                engine.clear();

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
                                .innerJoin("ItemSpecification", "S", "DS.itemSpecificationId", "S.id");

                engine.addSelect("R.id").addSelect("R.code").addSelect("R.receiveDate").addSelect("E.name")
                                .addSelect("E.lastName").addSelect("E.fatherName").addSelect("IR.name")
                                .addSelect("I.name").addSelect("ID.detail").addSelect("U.name").addSelect("S.price")
                                .addSelect("S.id").addSelect("S.serialNumber").addSelect("S.tagNumber")
                                .addSelect("S.location").addSelect("S.expirationDate").addSelect("DS.id");
                engine.where(WhereConditionType.NONE, "IDD.itemDistributionId", ConditionType.EQUAL,
                                request.getItemDistributionId());
                engine.where(WhereConditionType.AND, "I.consumable", ConditionType.EQUAL, false);
                List<ItemReceiptDistributedSpeceficationModel> nonConsumableList = engine
                                .execute(ItemReceiptDistributedSpeceficationModel.class);
                consumableList.forEach(e -> {
                        ItemReceiptDistributedSpeceficationModel model = new ItemReceiptDistributedSpeceficationModel();
                        model.setId(e.getId());
                        model.setCode(e.getCode());
                        model.setFatherName(e.getFatherName());
                        model.setItem(e.getItem());
                        model.setItemDetail(e.getItemDetail());
                        model.setItemReceiptTypeText(e.getItemReceiptTypeText());
                        model.setLastName(e.getLastName());
                        model.setName(e.getLastName());
                        model.setPrice(e.getPrice());
                        model.setReceiveDate(e.getReceiveDate());
                        model.setUnitText(e.getUnitText());
                        nonConsumableList.add(model);
                });
                return nonConsumableList;
        }
}
