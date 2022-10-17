package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationBoardModel;
import com.clean.application.itemreallocation.models.ItemReallocationDetailModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemReallocationDetail;
import com.clean.domain.entity.stc.ItemReallocationSpecification;
import com.clean.domain.entity.stc.ItemReceiptDetail;
import com.clean.persistence.itemreallocation.ItemReallocationDetailRepository;
import com.clean.persistence.itemreallocation.ItemReallocationSpecificationRepository;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  CreatePricingBoardCommandHandler implements IRequestHandler<CreatePricingBoardCommand, ItemReallocationBoardModel> {
    private ItemReallocationSpecificationRepository ItemReallocationSpecificationRepository;

    @Autowired
    CreatePricingBoardCommandHandler(
            ItemReallocationSpecificationRepository ItemReallocationSpecificationRepository
    )
    {
        this.ItemReallocationSpecificationRepository = ItemReallocationSpecificationRepository;
    }
    @Override
    public ItemReallocationBoardModel handle(CreatePricingBoardCommand request) {
        ItemReallocationSpecification itemReallocationSpecification = ItemReallocationSpecificationRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("جنس تحویل شده دریافت نگردید!"));

        itemReallocationSpecification.setPrice(request.getPrice());
        itemReallocationSpecification = ItemReallocationSpecificationRepository.save(itemReallocationSpecification);
        return ItemReallocationBoardModel.map(itemReallocationSpecification);
    }
}
