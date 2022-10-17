package com.clean.application.itemdistribution.quries;
import java.util.List;

import javax.persistence.EntityManager;

import com.clean.application.itemdistribution.models.ItemDistributionDetailPrintModel;
import com.clean.application.itemdistribution.models.ItemDistributionPrintModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.common.utility.PersianDate;
import com.clean.domain.entity.hr.StockKeeper;
import com.clean.domain.entity.stc.ItemDistribution;
import com.clean.domain.entity.stc.ItemRequest;
import com.clean.persistence.hr.StockKeeperRepository;
import com.clean.persistence.itemdistribution.ItemDistributionRepository;
import com.clean.persistence.itemrequest.ItemRequestRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;

import org.springframework.beans.factory.annotation.Autowired;

public class PrintItemDistributionQueryHandler implements IRequestHandler<PrintItemDistributionQuery,ItemDistributionPrintModel> {
    private ItemDistributionRepository itemDistributionRepository;
    private ItemRequestRepository itemRequestRepository;
    private EntityManager manager;
    private StockKeeperRepository stockKeeperRepository;
    @Autowired
    public PrintItemDistributionQueryHandler(
            ItemDistributionRepository itemDistributionRepository,
            ItemRequestRepository itemRequestRepository,
            EntityManager manager,
            StockKeeperRepository stockKeeperRepository
    ){
        this.itemDistributionRepository = itemDistributionRepository;
        this.itemRequestRepository = itemRequestRepository;
        this.manager=manager;
        this.stockKeeperRepository = stockKeeperRepository;
    }

    @Override
    public ItemDistributionPrintModel handle(PrintItemDistributionQuery request) {
        ItemDistribution itemDistribution = itemDistributionRepository.findByIdDetail(request.getId());

        if(itemDistribution == null){
            throw new RuntimeException("فورم توزیع دریافت نگردید!");
        }

        QueryEngine engine = new QueryEngine(manager);
        engine.from(("ItemDistributionDetail"), "D")
        .innerJoin("ItemDistributed", "ID", "D.id", "ID.itemDistributionDetailId")
        .innerJoin("ItemReceiptDetail", "RD", "ID.itemReceiptDetailId", "RD.id")
        .innerJoin("ItemReceipt", "R", "RD.itemReceiptId", "R.id")
        .innerJoin("ItemDetail", "I", "RD.itemDetail", "I.id");

        engine.where(WhereConditionType.NONE, "D.itemDistributionId", ConditionType.EQUAL, itemDistribution.getId());
        engine.addSelect("ID.quantity").addSelect("R.code").addSelect("R.stockKeeperId").addSelect("I.detail").addSelect("RD.price");

        List<ItemDistributionDetailPrintModel> list= engine.execute(ItemDistributionDetailPrintModel.class);

        ItemDistributionPrintModel itemDistributionModel = ItemDistributionPrintModel.map(itemDistribution);
        itemDistributionModel.setPrintModelList(list);
        itemDistributionModel.setItemRequestCode(itemDistribution.getItemRequest().getCode());
        itemDistributionModel.setItemRequestShamsiDate(PersianDate.gregorianToPersianString(itemDistribution.getItemRequest().getDate()));
        ItemRequest itemRequestDetail= itemRequestRepository.findEmployeeAndOrgUnitDetail(itemDistribution.getItemRequestId());
        itemDistributionModel.setEmployeeName(itemRequestDetail.getEmployee().getName());
        itemDistributionModel.setEmployeeLastName(itemRequestDetail.getEmployee().getLastName());
        itemDistributionModel.setEmployeeFatherName(itemRequestDetail.getEmployee().getFatherName());
        StockKeeper keeper =stockKeeperRepository.findStockKeeperDetail(list.get(0).getStockKeeperId());
        itemDistributionModel.setStockKeeperName(keeper.getEmployee().getName());
        itemDistributionModel.setStockKeeperLastName(keeper.getEmployee().getLastName());
        itemDistributionModel.setStockKeeperFatherName(keeper.getEmployee().getFatherName());
        itemDistributionModel.setItemRequestOrgUnitText(itemRequestDetail.getEmployee().getOrgUnit().getName());
        
        return itemDistributionModel;
    }
    
}
