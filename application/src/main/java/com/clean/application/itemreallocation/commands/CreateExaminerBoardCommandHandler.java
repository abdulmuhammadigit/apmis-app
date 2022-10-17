package com.clean.application.itemreallocation.commands;

import com.clean.application.itemreallocation.models.ItemReallocationBoardModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemReallocationSpecification;
import com.clean.persistence.itemreallocation.ItemReallocationSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateExaminerBoardCommandHandler implements IRequestHandler<CreateExaminerBoardCommand, ItemReallocationBoardModel> {
    private ItemReallocationSpecificationRepository itemReallocationSpecificationRepository;
    @Autowired
    CreateExaminerBoardCommandHandler(ItemReallocationSpecificationRepository itemReallocationSpecificationRepository){
        this.itemReallocationSpecificationRepository= itemReallocationSpecificationRepository;
    }
    @Override
    public ItemReallocationBoardModel handle(CreateExaminerBoardCommand request) {
        ItemReallocationSpecification itemReallocationSpecification = itemReallocationSpecificationRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("مشخصات جنس دریافت نگردید!"));
        itemReallocationSpecification.setBoardStatusId(request.getBoardStatusId());
        itemReallocationSpecification.setExaminerBoard(request.getExaminerBoard());
        itemReallocationSpecification = itemReallocationSpecificationRepository.save(itemReallocationSpecification);
        return ItemReallocationBoardModel.map(itemReallocationSpecification);
    }
}
