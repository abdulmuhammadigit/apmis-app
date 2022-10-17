package com.clean.application.itemreallocation.commands;

import java.util.List;

import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemReallocationDetail;
import com.clean.domain.entity.stc.ItemReallocationSpecification;
import com.clean.domain.entity.stc.ItemSpecification;
import com.clean.persistence.itemreallocation.ItemReallocationDetailRepository;
import com.clean.persistence.itemreallocation.ItemReallocationSpecificationRepository;
import com.clean.persistence.itemreceipt.ItemSpecificationRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateReallocationPriceCommandHandler implements IRequestHandler<CreateReallocationPriceCommand,Boolean> {
    private ItemReallocationDetailRepository itemReallocationDetailRepository;
    private ItemReallocationSpecificationRepository itemReallocationSpecificationRepository;
    private ItemSpecificationRepository itemSpecificationRepository;
    @Autowired
    CreateReallocationPriceCommandHandler(
            ItemReallocationDetailRepository itemReallocationDetailRepository,
            ItemReallocationSpecificationRepository itemReallocationSpecificationRepository,
            ItemSpecificationRepository itemSpecificationRepository
    ){
        this.itemReallocationDetailRepository = itemReallocationDetailRepository;
        this.itemReallocationSpecificationRepository = itemReallocationSpecificationRepository;
        this.itemSpecificationRepository = itemSpecificationRepository;
    }

    @Override
    public Boolean handle(CreateReallocationPriceCommand request) {
        List<ItemReallocationDetail> itemReallocationDetails = itemReallocationDetailRepository.findAllByItemReallocationId(request.getItemReallocationId());
        itemReallocationDetails.forEach(detail->{  
            List<ItemReallocationSpecification> specificationList = itemReallocationSpecificationRepository.findAllByItemReallocationDetailId(detail.getId());
            specificationList.forEach(e->{
    
               ItemSpecification specification = itemSpecificationRepository.findById(e.getItemDistributedSpecification().getItemSpecificationId()).get();
               specification.setPrice(e.getPrice());
               if(e.getBoardStatusId() == StatusMapped.REALLOCATION_SPECIFICATION_TO_USABLE){
                   specification.setStatusId(StatusMapped.SPECIFICATION_WAREHOUSE);
               }else{
                   specification.setStatusId(StatusMapped.SPECIFICATION_SEND_TO_AUCTION);
               }
               itemSpecificationRepository.save(specification);
    
            });
        });
         
        return true;
    }
    
}
