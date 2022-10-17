package com.clean.application.itemreallocation.commands;

import com.clean.common.constant.BusinessRuleMapped;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.ItemReallocation;
import com.clean.persistence.itemreallocation.ItemReallocationRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ProcessItemReallocationCheckCommandHandler implements IRequestHandler<ProcessItemReallocationCheckCommand,Boolean> {
    private IMediator mediator;
    private ItemReallocationRepository itemReallocationRepository;
    @Autowired
    public ProcessItemReallocationCheckCommandHandler(IMediator mediator, ItemReallocationRepository itemReallocationRepository) {
        this.mediator = mediator;
        this.itemReallocationRepository = itemReallocationRepository;
    }
    @Override
    public Boolean handle(ProcessItemReallocationCheckCommand request) {
        if(request.getBusinessRule().equals(BusinessRuleMapped.ReallocationApprove)){
            mediator.send(CreateReallocationApproveCommand.builder().itemReallocationId(request.getRecordId()).build());
        }
        else if(request.getBusinessRule().equals(BusinessRuleMapped.ReallocationPrice)){
            mediator.send(CreateReallocationPriceCommand.builder().itemReallocationId(request.getRecordId()).build());
        }

        ItemReallocation itemReallocation= itemReallocationRepository.findById(request.getRecordId()).get();
                itemReallocation.setStageId(request.getStageId()); 
                itemReallocationRepository.save(itemReallocation);
        return true;
    }
    
}
