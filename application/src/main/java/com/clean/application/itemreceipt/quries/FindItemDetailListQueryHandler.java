package com.clean.application.itemreceipt.quries;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.application.itemreceipt.models.ItemReceipItemDetailModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class FindItemDetailListQueryHandler implements IRequestHandler<FindItemDetailListQuery,List<ItemReceipItemDetailModel>> {
    private ItemReceiptDetailRepository itemReceiptDetailRepository;
    @Autowired
    public FindItemDetailListQueryHandler(ItemReceiptDetailRepository itemReceiptDetailRepository) {
        this.itemReceiptDetailRepository = itemReceiptDetailRepository;
    }
    @Override
    public List<ItemReceipItemDetailModel> handle(FindItemDetailListQuery request) {
        
       return itemReceiptDetailRepository.findByItemReceiptId(request.getItemReceiptId()).stream().map(map->{
            ItemReceipItemDetailModel model = new ItemReceipItemDetailModel();
            model.setId(map.getId());
            model.setItemDetailText(map.getItemDetail().getDetail());
            return model;
        }).collect(Collectors.toList());
    }
    
}
