package com.clean.application.itemrequest.commands;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.itemrequest.ItemRequestDetailRepository;
import com.clean.persistence.itemrequest.ItemRequestRepository;
import com.clean.persistence.processtracking.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteItemRequestDetailCommandHandler implements IRequestHandler<DeleteItemRequestDetailCommand,Boolean> {
    private ItemRequestDetailRepository detailRepository;
    private StageRepository stageRepository;
    private ItemRequestRepository itemRequestRepository;
    @Autowired
    public DeleteItemRequestDetailCommandHandler(ItemRequestDetailRepository detailRepository, StageRepository stageRepository,ItemRequestRepository itemRequestRepository) {
        this.detailRepository = detailRepository;
        this.stageRepository = stageRepository;
        this.itemRequestRepository= itemRequestRepository;
    }
    @Override
    public Boolean handle(DeleteItemRequestDetailCommand request) {
        //BRC:check it editable
        if(!stageRepository.findById(itemRequestRepository.findById(detailRepository.findById(request.getId()).get().getItemRequestId()).get().getStageId()).get().isEditable()){
            throw new RuntimeException("ریکارد قابل حذف نمیباشد!");
        }

        if(detailRepository.itemDistributed(request.getId()) != null){
            throw new RuntimeException("جنس توزیع شده را نمیتوانید حذف کنید!");
        }
        
        detailRepository.deleteById(request.getId());
        return true;
    }
    
}
