package com.clean.application.auction.quries;


import com.clean.application.itemreallocation.models.ItemReallocationSpecificationSearchModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.auction.AuctionRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import java.util.List;
@Service
public class SearchReallocatedItemQueryHandler implements IRequestHandler<SearchReallocatedItemQuery, List<ItemReallocationSpecificationSearchModel>> {
   private EntityManager manager;
   @Autowired
    SearchReallocatedItemQueryHandler(EntityManager manager){
       this.manager = manager;
   }
    @Override
    public List<ItemReallocationSpecificationSearchModel> handle(SearchReallocatedItemQuery request) {
        QueryEngine queryEngine = new QueryEngine(manager);
        queryEngine.from("ItemReallocationSpecification","irs");
        queryEngine.innerJoin("ItemDistributedSpecification","ids","irs.itemDistributedSpecificationId","ids.id")
                .innerJoin("ItemSpecification","isp","ids.itemSpecificationId","isp.id")
                .innerJoin("ItemReceiptDetail","ird","isp.itemReceiptDetailId","ird.id")
                .innerJoin("ItemDetail","ide","ird.itemDetailId","ide.id")
        ;
        if (request.getItemDetail() != null && !request.getItemDetail().isEmpty()) {
            queryEngine.where(WhereConditionType.NONE, "ide.detail", ConditionType.EQUAL, request.getItemDetail());
        }
        if (request.getSerialNumber() !=null && !request.getSerialNumber().isEmpty() ) {
            queryEngine.where(WhereConditionType.AND, "isp.serialNumber", ConditionType.EQUAL, request.getSerialNumber());
        }
        if (request.getTagNumber() !=null && !request.getSerialNumber().isEmpty() ) {
            queryEngine.where(WhereConditionType.AND, "isp.tagNumber", ConditionType.EQUAL, request.getTagNumber());
        }
        queryEngine
                .addSelect("isp.id")
                .addSelect("ide.id")
                .addSelect("ide.detail")
                .addSelect("irs.id")
                .addSelect("isp.serialNumber")
                .addSelect("isp.tagNumber")
                .addSelect("isp.location")
                .addSelect("isp.expirationDate")
                .addSelect("isp.depreciationDay")
                .addSelect("isp.itemReceiptDetailId")
                .addSelect("isp.statusId")
                .addSelect("irs.price")
        ;
        return queryEngine.execute(ItemReallocationSpecificationSearchModel.class);
    }
}
