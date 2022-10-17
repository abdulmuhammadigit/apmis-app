package com.clean.application.hr.queries;

import com.clean.application.hr.models.StockKeeperModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.hr.StockKeeperRepository;
import com.clean.persistence.look.CategoryRepository;
import com.clean.persistence.look.LocationRepository;
import com.clean.persistence.look.StockKeeperTypeRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import java.util.List;

public class SearchStockKeeperQueryHandler implements IRequestHandler<SearchStockKeeperQuery, List<StockKeeperModel>> {

    private StockKeeperRepository stockKeeperRepository;
    private StockKeeperTypeRepository stockKeeperTypeRepository;
    private LocationRepository locationRepository;
    private CategoryRepository categoryRepository;
    private EntityManager manager;
    SearchStockKeeperQueryHandler(
            StockKeeperRepository stockKeeperRepository,
            StockKeeperTypeRepository stockKeeperTypeRepository,
            LocationRepository locationRepository,
            CategoryRepository categoryRepository,
            EntityManager manager)
    {
        this.stockKeeperRepository = stockKeeperRepository;
        this.stockKeeperTypeRepository  = stockKeeperTypeRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.manager = manager;
    }
    @Override
    public  List<StockKeeperModel> handle(SearchStockKeeperQuery request) {
        QueryEngine engine = new QueryEngine(this.manager);
        engine.from("StockKeeper","SK")
                .innerJoin("Employee","E","SK.employeeId","E.id")
                .leftJoin("Location","L","SK.locationId","L.id")
                .innerJoin("StockKeeperType","SKT","SK.stockKeeperTypeId","SKT.id")
                .innerJoin("Category","C","SK.itemCategoryId","C.id")
        ;

        if (request.getStockKeeperTypeId()!=0){
            engine.where(WhereConditionType.AND,"SK.stockKeeperTypeId",ConditionType.EQUAL,request.getStockKeeperTypeId());
        }

        engine.addSelect("SK.id")
                .addSelect("SK.employeeId")
                .addSelect("SK.stockKeeperTypeId")
                .addSelect("SK.itemCategoryId")
                .addSelect("SK.locationId")
                .addSelect("SK.userId")
                .addSelect("E.name")
                .addSelect("E.lastName")
                .addSelect("E.fatherName")
                .addSelect("C.name")
                .addSelect("SKT.name")
                .addSelect("L.name");
        List<StockKeeperModel> models = engine.execute(StockKeeperModel.class);
        return models;
    }
}

