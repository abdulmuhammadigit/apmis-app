package com.clean.application.itemreceipt.quries;

import com.clean.application.itemreceipt.models.ItemSpecificationModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.itemreceipt.ItemSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FindItemSpecificationQueryHandler implements IRequestHandler<FindItemSpecificationQuery, List<ItemSpecificationModel>> {
    private ItemSpecificationRepository repository;
    @Autowired
    FindItemSpecificationQueryHandler(ItemSpecificationRepository repository){
        this.repository= repository;
    }
    @Override
    public List<ItemSpecificationModel> handle(FindItemSpecificationQuery request) {
        List<ItemSpecificationModel> itemSpecificationModelList = new ArrayList<>();
        repository.findAllByItemReceiptDetailId(request.getItemReceiptDetailId()).forEach(
                specification->
                        itemSpecificationModelList.add(ItemSpecificationModel.map(specification))
        );

        return itemSpecificationModelList; 
    }
}
