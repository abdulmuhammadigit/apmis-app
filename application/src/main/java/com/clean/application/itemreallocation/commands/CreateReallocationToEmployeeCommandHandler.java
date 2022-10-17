package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationBoardModel;
import com.clean.application.itemreallocation.models.ItemReallocationSpecificationModel;
import com.clean.common.constant.StatusMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemReallocationDetail;
import com.clean.domain.entity.stc.ItemReallocationSpecification;
import com.clean.persistence.itemreallocation.ItemReallocationDetailRepository;
import com.clean.persistence.itemreallocation.ItemReallocationSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateReallocationToEmployeeCommandHandler implements IRequestHandler<CreateReallocationToEmployeeCommand, ItemReallocationBoardModel> {
   private ItemReallocationSpecificationRepository itemReallocationSpecificationRepository;
   private ItemReallocationDetailRepository itemReallocationDetailRepository;
   @Autowired
   public CreateReallocationToEmployeeCommandHandler(
           ItemReallocationSpecificationRepository itemReallocationSpecificationRepository,
           ItemReallocationDetailRepository itemReallocationDetailRepository
   ){
       this.itemReallocationSpecificationRepository= itemReallocationSpecificationRepository;
       this.itemReallocationDetailRepository = itemReallocationDetailRepository;
   }
    @Override
    public ItemReallocationBoardModel handle(CreateReallocationToEmployeeCommand request) {
        //BRC: check for quantity to be saved
        int specificationCount = itemReallocationSpecificationRepository.countByItemReallocationDetailId(request.getItemReallocationDetailId());
        int detailCount = itemReallocationDetailRepository.findById(request.getItemReallocationDetailId()).get().getReallocationQuantity();
        if((specificationCount == detailCount)){
            if(request.getId() == 0){
                throw new RuntimeException("مقدار اجناس که باید تحویل گردد قبلاً ثبت گردیده است!");
            }
        }

        ItemReallocationSpecification itemReallocationSpecification = itemReallocationSpecificationRepository.findById(request.getId()).orElse(null);
        if(itemReallocationSpecification == null){
            itemReallocationSpecification = new ItemReallocationSpecification();
        }
        if(request.getStatusId() == StatusMapped.REALLOCATION_TO_EMPLOYEE){
            //‌BRC:check for to employee
            if(request.getToEmployeeId() == null || request.getToEmployeeId() ==0){
                throw new RuntimeException("لطف نموده شخص که جنس را تحویل میگیرد مشخص سازید!");
            }
            itemReallocationSpecification.setToEmployeeId(request.getToEmployeeId());
            
        }else{
            itemReallocationSpecification.setToEmployeeId(null);
        }
        itemReallocationSpecification.setItemDistributedSpecificationId(request.getItemDistributedSpecificationId());
        itemReallocationSpecification.setItemReallocationDetailId(request.getItemReallocationDetailId());
        itemReallocationSpecification.setStatusId(request.getStatusId());
        itemReallocationSpecification = itemReallocationSpecificationRepository.save(itemReallocationSpecification);
        ItemReallocationBoardModel  model = ItemReallocationBoardModel.map(itemReallocationSpecification);
        return model; 
    }
}
