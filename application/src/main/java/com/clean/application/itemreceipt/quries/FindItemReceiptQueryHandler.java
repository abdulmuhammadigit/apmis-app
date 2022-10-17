package com.clean.application.itemreceipt.quries;

import com.clean.application.itemreceipt.models.ItemReceiptDetailModel;
import com.clean.application.itemreceipt.models.ItemReceiptModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.StockKeeper;
import com.clean.domain.entity.stc.ItemReceipt;
import com.clean.persistence.hr.StockKeeperRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.itemreceipt.ItemReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class FindItemReceiptQueryHandler implements IRequestHandler<FindItemReceiptQuery, ItemReceiptModel> {

    private ItemReceiptRepository repository;
    private ItemReceiptDetailRepository repositoryDetail;
    private ItemRepository itemRepository;
    private StockKeeperRepository stockKeeperRepository;

    @Autowired
    public FindItemReceiptQueryHandler(ItemReceiptRepository repository, ItemReceiptDetailRepository repositorydetail ,ItemRepository itemRepository,StockKeeperRepository stockKeeperRepository){
        this.repository = repository;
        this.repositoryDetail = repositorydetail;
        this.itemRepository = itemRepository;
        this.stockKeeperRepository = stockKeeperRepository;
    }

    @Override
    public ItemReceiptModel handle(FindItemReceiptQuery request) {

        ItemReceipt itemReceipt = repository.findById(request.getId()).orElseThrow(() -> new RuntimeException("فورم راپور رسید دریافت نگردید!"));
        List<ItemReceiptDetailModel> itemReceiptDetaillList = repositoryDetail.findByItemReceiptId(itemReceipt.getId())
                .stream().map(item -> {
                    ItemReceiptDetailModel detailModel  = ItemReceiptDetailModel.map(item);
                    detailModel.setItemDetailText(item.getItemDetail().getDetail());
                    detailModel.setConsumable(itemRepository.findItemByItemDetial(item.getItemDetailId()).getConsumable());
                    detailModel.setUnitText(item.getUnit().getName());
                    detailModel.setBaseUnitText(itemRepository.findItemByItemDetial(detailModel.getItemDetailId()).getUnit().getName());
                    return detailModel;
                }).collect(Collectors.toList());
        ItemReceiptModel model=ItemReceiptModel.map(itemReceipt);
        StockKeeper keeper =stockKeeperRepository.findStockKeeperDetail(model.getStockKeeperId());
        model.setStockKeeperName(keeper.getEmployee().getName());
        model.setStockKeeperLastname(keeper.getEmployee().getLastName());
        model.setStockKeeperFatherName(keeper.getEmployee().getFatherName());
        model.setItemDetailList(itemReceiptDetaillList);
        return model;
    }
}



