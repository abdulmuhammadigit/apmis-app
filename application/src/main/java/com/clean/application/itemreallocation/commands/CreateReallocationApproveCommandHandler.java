package com.clean.application.itemreallocation.commands;

import java.util.List;

import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemDistributed;
import com.clean.domain.entity.stc.ItemDistributedSpecification;
import com.clean.domain.entity.stc.ItemReallocationDetail;
import com.clean.domain.entity.stc.ItemReallocationSpecification;
import com.clean.domain.entity.stc.ItemReceiptDetail;
import com.clean.persistence.itemdistribution.ItemDistributedRepository;
import com.clean.persistence.itemdistribution.ItemDistributedSpecificationRepository;
import com.clean.persistence.itemreallocation.ItemReallocationDetailRepository;
import com.clean.persistence.itemreallocation.ItemReallocationSpecificationRepository;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateReallocationApproveCommandHandler implements IRequestHandler<CreateReallocationApproveCommand ,Boolean> {
    private ItemReallocationDetailRepository itemReallocationDetailRepository;
    private ItemReceiptDetailRepository itemReceiptDetailRepository;
    private ItemReallocationSpecificationRepository itemReallocationSpecificationRepository;
    private ItemDistributedSpecificationRepository itemDistributedSpecificationRepository;
    private ItemDistributedRepository itemDistributedRepository;

    @Autowired
    CreateReallocationApproveCommandHandler(
            ItemReallocationDetailRepository itemReallocationDetailRepository,
            ItemReceiptDetailRepository itemReceiptDetailRepository,
            ItemReallocationSpecificationRepository itemReallocationSpecificationRepository,
            ItemDistributedSpecificationRepository itemDistributedSpecificationRepository,
            ItemDistributedRepository itemDistributedRepository
    )
    {
        this.itemReallocationDetailRepository = itemReallocationDetailRepository;
        this.itemReceiptDetailRepository = itemReceiptDetailRepository;
        this.itemReallocationSpecificationRepository = itemReallocationSpecificationRepository;
        this.itemDistributedSpecificationRepository = itemDistributedSpecificationRepository;
        this.itemDistributedRepository = itemDistributedRepository;
    }
    @Override
    public Boolean handle(CreateReallocationApproveCommand request) {
        List<ItemReallocationDetail> itemReallocationDetails = itemReallocationDetailRepository.findAllByItemReallocationId(request.getItemReallocationId());
        itemReallocationDetails.forEach(detail->{  
            ItemReallocationDetail itemReallocationDetail = detail;
            List<ItemReallocationSpecification> specificationList = itemReallocationSpecificationRepository.findAllByItemReallocationDetailId(detail.getId());
            
            if(itemReallocationDetail.getReallocationQuantity() != specificationList.size()){
                throw new RuntimeException("لطف نموده نخست تمام اجناس قابل تحویل را ثبت نمائید!");
            }
    
            specificationList.forEach(e->{
    
                ItemDistributedSpecification itemDistributedSpecification = itemDistributedSpecificationRepository.findById(e.getItemDistributedSpecificationId()).get();
                if(itemDistributedSpecification.getStatusId() == StatusMapped.REALLOCATION_DETAIL_REALLOCATED){
                    throw new RuntimeException("اجناس تائید گردید اند!");
                }
                itemDistributedSpecification.setStatusId(StatusMapped.REALLOCATION_DETAIL_REALLOCATED);
                itemDistributedSpecificationRepository.save(itemDistributedSpecification);
    
               if(e.getToEmployeeId() == null){
                    ItemDistributed itemDistributed=itemDistributedRepository.findById(itemDistributedSpecification.getItemDistributedId()).get();
                    ItemReceiptDetail itemReceiptDetail = itemReceiptDetailRepository.findById(itemDistributed.getItemReceiptDetailId()).get();
                   itemReceiptDetail.setRemain((short) (itemReceiptDetail.getRemain() + 1));
                   itemReceiptDetail.setFinished(false);
                   itemReceiptDetailRepository.save(itemReceiptDetail);
               }
    
            });
            itemReallocationDetail.setConfirmed(true);
            itemReallocationDetailRepository.save(itemReallocationDetail);
        });
         
        return true;
    }
}
