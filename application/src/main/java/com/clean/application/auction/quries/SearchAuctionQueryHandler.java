package com.clean.application.auction.quries;

import com.clean.application.auction.models.AuctionModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchAuctionQueryHandler implements IRequestHandler<SearchAuctionQuery, List<AuctionModel>> {
    private EntityManager manager;
    @Autowired
    public SearchAuctionQueryHandler(EntityManager manager){
        this.manager = manager;
    }
    @Override
    public List<AuctionModel> handle(SearchAuctionQuery request) {
        QueryEngine queryEngine = new QueryEngine(manager);
        queryEngine.from("Auction", "L");
        queryEngine.innerJoin("ItemDetail", "I", "L.itemDetailId", "I.id")
                .innerJoin("Item","Itm","I.itemId","Itm.id")
                .innerJoin("Status", "S", "L.statusId", "S.id")
                .innerJoin("FiscalYear","F","L.fiscalYearId","F.id")

        ;

        if (request.getItemDetailId() != null && request.getItemDetailId() !=0) {
            queryEngine.where(WhereConditionType.NONE, "L.itemDetailId", ConditionType.EQUAL, request.getItemDetailId());
        }

        if (request.getStatusId() != 0 ) {
            queryEngine.where(WhereConditionType.AND, "L.statusId", ConditionType.EQUAL, request.getStatusId());
        }
        if (request.getFiscalYearId() != 0 ) {
            queryEngine.where(WhereConditionType.AND, "L.fiscalYearId", ConditionType.EQUAL, request.getFiscalYearId());
        }
        queryEngine
                .addSelect("L.id")
                .addSelect("Itm.id")
                .addSelect("L.itemDetailId")
                .addSelect("L.quantity")
                .addSelect("L.statusId")
                .addSelect("L.date")
                .addSelect("L.fiscalYearId")
                .addSelect("L.price")
                .addSelect("L.pricingBoard")
                .addSelect("L.finalPrice")
                .addSelect("I.detail")
                .addSelect("S.name")
                .addSelect("F.shamsiYear")
                .addSelect("Itm.name")
        ;
        return queryEngine.execute(AuctionModel.class);
    }
}
